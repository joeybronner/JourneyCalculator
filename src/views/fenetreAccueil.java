package views;

import aStar.AStar;
import aStar.AreaMap;
import aStar.heuristics.AStarHeuristic;
import aStar.heuristics.ClosestHeuristic;
import aStar.utils.Compteur;
import aStar.utils.Console;
import aStar.utils.Scrapper;
import connect.DatabaseConnect;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.Collection;

public class fenetreAccueil extends JFrame {

    /**
     * Variables
     *
     * @param mapWith       Largeur de la carte
     * @param mapHeight     Hauteur de la carte
     * @param obstacleMap   Rang�e de la pi�ce � d�placer
     * @param startX        Point de d�part
     * @param startY        Point de d�part
     * @param goalX         Point de destination
     * @param goalY         Point de destination
     */
    private static int mapLarg = 50;
    private static int mapHaut = 50;
    private static int depaX = 11;
    private static int depaY = 4;
    private static int destX = 21;
    private static int destY = 17;
    private static int typeItineraire = 99;

    public fenetreAccueil() {
        super();

        build();//On initialise notre fenêtre
    }

    public static void main(String[] args) {
        fenetreAccueil f = new fenetreAccueil();

        DatabaseConnect BDD = new DatabaseConnect();
        Console log = new Console();
        Compteur chrono = new Compteur();
        chrono.demarrer();

        log.ecrireConsole("Ouverture de la connexion avec la base de donn�es...");
        BDD.Connexion();

        log.ecrireConsole("Initialisation de la carte...");
        AreaMap map = new AreaMap(mapLarg, mapHaut);

        log.ecrireConsole("Initialisation de l'heuristique...");
        AStarHeuristic heuristic = new ClosestHeuristic();

        log.ecrireConsole("Initialisation de l'algorithme...");
        AStar pathFinder = new AStar(map, heuristic);

        log.ecrireConsole("Calcul du chemin le plus court...");

        while (typeItineraire != 1 && typeItineraire != 2) {
            typeItineraire = log.poserQuestionOneTwo("Voulez-vous privil�gier :\n -Le distance ? (1)\n -Le nombre de changements ? (2)");
            if (typeItineraire == 1) {
                pathFinder.calcShortestPath(depaX, depaY, destX, destY);
            } else if (typeItineraire == 2) {
                pathFinder.calcCoolestPath(depaX, depaY, destX, destY);
            }
        }

        chrono.arreter();
        log.ecrireConsole("Dur�e de calcul: " + chrono.getDureeMilliSec() + "ms");

        log.ecrireConsole("\nR�sultat:");
        //pathFinder.printPath();
        pathFinder.printItineraire();
    }

    private void build() {
        setTitle("Itinéraire"); //On donne un titre à l'application
        setSize(320, 240); //On donne une taille à notre fenêtre
        setLocationRelativeTo(null); //On centre la fenêtre sur l'écran
        setResizable(false); //On interdit la redimensionnement de la fenêtre
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //On dit à l'application de se fermer lors du clic sur la croix
        setLayout(new BorderLayout());
        JPanel chemin = new JPanel();
        chemin.setLayout(new BorderLayout());
        JPanel choix = new JPanel();
        JLabel l = new JLabel("Mode de transport");
        choix.add(l);
        JCheckBox metro = new JCheckBox("Métro");
        metro.setSelected(false);

        JCheckBox tram = new JCheckBox("Tram");
        tram.setSelected(false);

        JCheckBox rer = new JCheckBox("RER");
        rer.setSelected(false);

        JCheckBox all = new JCheckBox("All");
        all.setSelected(true);
        choix.add(metro);
        choix.add(rer);
        choix.add(tram);
        choix.add(all);
        chemin.add(choix, BorderLayout.NORTH);

        JPanel depart = new JPanel();
        JPanel arrive = new JPanel();

        Scrapper s = new Scrapper();
        try {
            s.readStops();
            Collection<Scrapper.Arret> a = s.getArrs().values();
            JComboBox listeDep = new JComboBox(a.toArray());
            JComboBox listeArr = new JComboBox(a.toArray());

            depart.add(listeDep);
            arrive.add(listeArr);
            chemin.add(listeDep, BorderLayout.CENTER);
            chemin.add(listeArr, BorderLayout.SOUTH);

        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
        add(chemin, BorderLayout.NORTH);
        JButton valider = new JButton("Valider");
        add(valider, BorderLayout.SOUTH);
        pack();
        setVisible(true);
    }

}
