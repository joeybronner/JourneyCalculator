package aStar.utils;

import java.io.*;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

import connect.DatabaseConnect;

public class Scrapper
{

	static Map<Integer, Arret> arrs = new HashMap<Integer, Arret>();
    static ArrayList<Line> lines = new ArrayList<Line>();
    static InputStream fis;
    static BufferedReader br;
    static String line;
    static Pattern pattern = Pattern.compile(";");

    public static Map<Integer, Arret> getArrs() {
        return arrs;
    }

    public static ArrayList<Line> getLines() {
        return lines;
    }
    
    public static void MAJCoordonnees() throws IOException
    {
    	
    	int compteur= 0;
    	DatabaseConnect BDD = new DatabaseConnect();
    	BDD.Connexion();
    	BDD.ViderTable("tb_coordonnees");
        fis = new FileInputStream("res/coordonnes.csv");
        br = new BufferedReader(new InputStreamReader(fis, Charset.forName("UTF-8")));
        System.out.println("Mise ‡ jour de la table tb_coordonnees en cours... Veuillez patienter...");
        while ((line = br.readLine()) != null)
        {
            String array[] = pattern.split(line, 0);
            if (array.length == 3)
            {
                Arret a = arrs.get(Integer.parseInt(array[0]));
                if (a != null) {
                    a.x = Integer.parseInt(array[1]);
                    a.y = Integer.parseInt(array[2]);
                }
                
                try {
                	BDD.Ajouter("INSERT INTO tb_coordonnees VALUES (" + a.id + "," + a.x + "," + a.y + ")");
                }
                catch (Exception err){
                	// Erreur
                }
                
            }    
        compteur++;         
        }
        System.out.println("Mise ‡ jour terminÈe. " + compteur + "lignes ont ÈtÈ ajoutÈes.");
        BDD.Deconnexion();
    }

    public static void readStops() throws IOException
    {


        // Stops
        fis = new FileInputStream("res/stops.csv");
        br = new BufferedReader(new InputStreamReader(fis, Charset.forName("UTF-8")));

        Arret arr;
        Line li;
        while ((line = br.readLine()) != null) {
            String array[] = pattern.split(line, 0);
            arr = new Arret(array[0], array[3], array[5]);
            arrs.put(Integer.valueOf(array[0]), arr);
        }

        // lines
        fis = new FileInputStream("res/lines.csv");
        br = new BufferedReader(new InputStreamReader(fis, Charset.forName("UTF-8")));
        while ((line = br.readLine()) != null) {
            String array[] = pattern.split(line, 0);
            li = new Line(array[1], array[2]);
            if (lines.contains(li))
                li = lines.get(lines.indexOf(li));
            Arret a = arrs.get(Integer.parseInt(array[0]));
            li.arrets.add(a);
            lines.add(li);
        }

        br.close();
    }

    public static void main(String[] args) {
        try {
            readStops();
        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }

    static class Line {
        String nom; // Num√©ro ( d√©part / d√©part - arriv√©e - arriv√©e )
        String type;
        ArrayList<Arret> arrets;

        public Line(String s1, String s2) {
            nom = s1;
            type = s2;
            arrets = new ArrayList<Arret>();

        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Line line = (Line) o;

            if (!nom.equals(line.nom)) return false;
            if (!type.equals(line.type)) return false;

            return true;
        }

        @Override
        public int hashCode()
        {
            int result = nom.hashCode();
            result = 31 * result + type.hashCode();
            return result;
        }
    }

    public static class Arret {
        int id;
        // virer lat long
        String nom;
        // Virer lieu
        String type;
        int x;
        int y;

        public Arret(String s, String s1, String s2) {
            id = Integer.parseInt(s);
            nom = s1;
            type = s2;
        }

        @Override
        public String toString() {
            return "[" + type + "] " + nom;
        }
    }

}
