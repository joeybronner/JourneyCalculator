package aStar.utils;

import java.io.*;
import java.nio.charset.Charset;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

import connect.DatabaseConnect;

public class Scrapper
{

	static Map<Integer, Arret> arrs = new HashMap<Integer, Arret>();
	static Map<Integer, Station> stat = new HashMap<Integer, Station>();
    static ArrayList<Line> lines = new ArrayList<Line>();
    static InputStream fis;
    static BufferedReader br;
    static String line;
    static Pattern pattern = Pattern.compile(";");
    static int compteur;
    
    static int stop_id;
    static int stop_code;
	static String stop_name;
	static String stop_desc;
	static int stop_lat;
	static int stop_lon;
	static int location_type;
	static int parent_station;

    public static Map<Integer, Arret> getArrs() {
        return arrs;
    }

    public static ArrayList<Line> getLines() {
        return lines;
    }
    
    public static Map<Integer, Station> getStations() {
        return stat;
    }
    
    public static void MAJCoordonnees() throws IOException
    {
    	compteur= 0;
    	DatabaseConnect BDD = new DatabaseConnect();
    	BDD.Connexion();
    	BDD.ViderTable("tb_stopscoordon");
        fis = new FileInputStream("res/coordonnees.csv");
        br = new BufferedReader(new InputStreamReader(fis, Charset.forName("UTF-8")));
        System.out.println("Mise a jour de la table <tb_stopscoordon> en cours...\nVeuillez patienter...");
        while ((line = br.readLine()) != null)
        {
            String array[] = pattern.split(line, 0);
            if (array.length == 3)
            {
                try {
                	BDD.Ajouter("INSERT INTO tb_stopscoordon VALUES (" + array[0] + "," + array[1] + "," + array[2] + ")");
                	compteur++;   
                }
                catch (Exception err){} 
            }    
              
        }
        System.out.println("Mise a jour terminee.\n" + compteur + " lignes ont ete ajoutees.");
        BDD.Deconnexion();
    }

    public static void MAJStops() throws IOException
    {
    	compteur= 0;
    	DatabaseConnect BDD = new DatabaseConnect();
    	BDD.Connexion();
    	BDD.ViderTable("tb_stops");
        fis = new FileInputStream("res/stops.csv");
        br = new BufferedReader(new InputStreamReader(fis, Charset.forName("UTF-8")));
        System.out.println("Mise a jour de la table <tb_stops> en cours...\nVeuillez patienter...");
        while ((line = br.readLine()) != null)
        {
            String array[] = pattern.split(line, 0);
            if (array.length == 8)
            {     	
                try {        
                	
                   	BDD.Ajouter("INSERT INTO tb_stops VALUES ("
                			         +  array[0] + ", "
                			         +  array[1] + ", '"
                			         +  array[2].replace("'", "\\'") + "', '"
                			         +  array[3].replace("'", "\\'") + "', "
                			         +  array[4] + ", "
                			         +  array[5] + ", "
                			         +  array[6] + ", "
                			         +  array[7] + ")");
                	compteur++;  
                }
                catch (Exception err){} 
            }         
        }
        System.out.println("Mise a jour terminee.\n" + compteur + " lignes ont ete ajoutees.");
        BDD.Deconnexion();
    }
    
    public static void MAJType() throws IOException
    {
    	compteur= 0;
    	DatabaseConnect BDD = new DatabaseConnect();
    	BDD.Connexion();
    	BDD.ViderTable("tb_stopstype");
        fis = new FileInputStream("res/type.csv");
        br = new BufferedReader(new InputStreamReader(fis, Charset.forName("UTF-8")));
        System.out.println("Mise a jour de la table <tb_stopstype> en cours...\nVeuillez patienter...");
        while ((line = br.readLine()) != null)
        {
            String array[] = pattern.split(line, 0);
            if (array.length == 2)
            {     	
                try {        
                	
                   	BDD.Ajouter("INSERT INTO tb_stopstype VALUES ("
                			         +  array[0] + ", '"
                			         +  array[1].replace("'", "\\'") + "')");

                	compteur++;  
                }
                catch (Exception err){} 
            }         
        }
        System.out.println("Mise a jour terminee.\n" + compteur + " lignes ont ete ajoutees.");
        BDD.Deconnexion();
    }
    
    public static void MAJLines() throws IOException
    {
    	compteur= 0;
    	DatabaseConnect BDD = new DatabaseConnect();
    	BDD.Connexion();
    	BDD.ViderTable("tb_lines");
        fis = new FileInputStream("res/lines.csv");
        br = new BufferedReader(new InputStreamReader(fis, Charset.forName("UTF-8")));
        System.out.println("Mise a jour de la table <tb_lines> en cours...\nVeuillez patienter...");
        while ((line = br.readLine()) != null)
        {
            String array[] = pattern.split(line, 0);
            if (array.length == 5)
            {
                try {
                	BDD.Ajouter("INSERT INTO tb_lines VALUES ('" + array[0] + "'," + array[1] + "," + array[2] + ", '" + array[3].replace("'", "\\'") + "', '" + array[4].replace("'", "\\'") + "')");
                	compteur++;  
                }
                catch (Exception err){} 
            }         
        }
        System.out.println("Mise a jour terminee.\n" + compteur + " lignes ont ete ajoutees.");
        BDD.Deconnexion();
    }

    public static void MAJNeighbors() throws IOException {
    	compteur= 0;
    	DatabaseConnect BDD = new DatabaseConnect();
    	BDD.Connexion();
    	BDD.ViderTable("tb_stopsneighbors");
        fis = new FileInputStream("res/neighbors.csv");
        br = new BufferedReader(new InputStreamReader(fis, Charset.forName("UTF-8")));
        System.out.println("Mise a jour de la table <tb_stopsneighbors> en cours...\nVeuillez patienter...");
        while ((line = br.readLine()) != null)
        {
            String array[] = pattern.split(line, 0);
            if (array.length == 3)
            {
                try {
                	BDD.Ajouter("INSERT INTO tb_stopsneighbors VALUES (" + array[0] + "," + array[1] + "," + array[2] + ")");
                	compteur++;  
                }
                catch (Exception err){} 
            }         
        }
        System.out.println("Mise a jour terminee.\n" + compteur + " lignes ont ete ajoutees.");
        BDD.Deconnexion();
    }
    
    public static void readStops(String requete) throws IOException, SQLException
    {
    	DatabaseConnect connexion = new DatabaseConnect();
    	connexion.Connexion();
    	Arret arr;
    	arrs.clear();
    	
    	ResultSet test = connexion.Rechercher(requete);
    	
    	int i = 0;
    	while (test.next())
    	{              
            arr = new Arret(test.getString("stop_id"), test.getString("stop_name"), test.getString("stop_type"));
            arrs.put(Integer.valueOf(test.getString("parent_id")), arr);
    	}
    	connexion.Deconnexion();
    }
    
    public static void readStations(String requete) throws IOException, SQLException
    {
    	DatabaseConnect connexion = new DatabaseConnect();
    	connexion.Connexion();
    	Station sta;
    	stat.clear();
    	
    	ResultSet test = connexion.Rechercher(requete);
    	
    	int i = 0;
    	while (test.next())
    	{              
    		sta = new Station(test.getString("parent_id"), test.getString("stop_type"), test.getString("stop_name"));
            stat.put(Integer.valueOf(test.getString("parent_id")), sta);
    	}
    	connexion.Deconnexion();
    }

    public static void main(String[] args) throws SQLException {
//        try {
//            readStops();
//        } catch (IOException e) {
//            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
//        }
    }

    static class Line {
        String nom; // Numéro ( départ / départ - arrivée - arrivée )
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
        float longit;
        float latitu;
        String nom;
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

    public static class Station {
        int id;
        String nom;
        String type;
        int x;
        int y;

        public Station(String s, String s1, String s2) {
            id = Integer.parseInt(s);
            type = s1;
            nom = s2;
        }

        @Override
        public String toString() {
            return "[" + type + "] " + nom;
        }
    }
}
