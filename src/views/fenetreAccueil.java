package views;

import javax.swing.*;
import connect.DatabaseConnect;
import aStar.AStar;
import aStar.AreaMap;
import aStar.heuristics.AStarHeuristic;
import aStar.heuristics.ClosestHeuristic;
import aStar.utils.Compteur;
import aStar.utils.Console;

import java.awt.*;
import java.awt.event.KeyEvent;

public class fenetreAccueil extends JFrame {

	/**
	 * Variables
	 * @param mapWith       Largeur de la carte
	 * @param mapHeight     Hauteur de la carte
	 * @param obstacleMap   Rangée de la pièce à déplacer
	 * @param startX        Point de départ
	 * @param startY        Point de départ
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

        build();//On initialise notre fenÃªtre
    }

    public static void main(String[] args) {
        fenetreAccueil f = new fenetreAccueil();
        
        DatabaseConnect BDD = new DatabaseConnect();      
        Console log = new Console();
        Compteur chrono = new Compteur();
        chrono.demarrer();
        
        log.ecrireConsole("Ouverture de la connexion avec la base de données...");
        BDD.Connexion();
        
        log.ecrireConsole("Initialisation de la carte...");
        AreaMap map = new AreaMap(mapLarg, mapHaut);
        
        log.ecrireConsole("Initialisation de l'heuristique...");
        AStarHeuristic heuristic = new ClosestHeuristic();
        
        log.ecrireConsole("Initialisation de l'algorithme...");
        AStar pathFinder = new AStar(map, heuristic);
        
        log.ecrireConsole("Calcul du chemin le plus court...");
        
        while (typeItineraire != 1 && typeItineraire != 2)
        {
        	typeItineraire = log.poserQuestionOneTwo("Voulez-vous privilégier :\n -Le distance ? (1)\n -Le nombre de changements ? (2)");
        	if (typeItineraire == 1)
        	{
        		pathFinder.calcShortestPath(depaX, depaY, destX, destY);
        	}
        	else if (typeItineraire == 2)
        	{
        		pathFinder.calcCoolestPath(depaX, depaY, destX, destY);
        	}
        }
        
        chrono.arreter();
        log.ecrireConsole("Durée de calcul: " + chrono.getDureeMilliSec() + "ms");
        
        log.ecrireConsole("\nRésultat:");
        //pathFinder.printPath();
        pathFinder.printItineraire();
    }

    private void build() {
        setTitle("ItinÃ©raire"); //On donne un titre Ã  l'application
        setSize(320, 240); //On donne une taille Ã  notre fenÃªtre
        setLocationRelativeTo(null); //On centre la fenÃªtre sur l'Ã©cran
        setResizable(false); //On interdit la redimensionnement de la fenÃªtre
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //On dit Ã  l'application de se fermer lors du clic sur la croix
        setLayout(new GridLayout());
        JPanel choix = new JPanel();
        JLabel l = new JLabel("Mode de transport");
        choix.add(l);
        JCheckBox metro = new JCheckBox("MÃ©tro");
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
        add(choix);
        setVisible(true);
    }

}
