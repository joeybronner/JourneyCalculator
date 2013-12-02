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
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Collection;

public class fenetreAccueil extends JFrame implements ActionListener {

    static Console log = new Console();
    static AStar pathFinder;
    static Compteur chrono = new Compteur();
    /**
     * Variables
     *
     * @param mapWith       Largeur de la carte
     * @param mapHeight     Hauteur de la carte
     * @param obstacleMap   Rangée de la piéce é déplacer
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
    JMenuBar barreMenu;
    JMenu maj;
    JMenuItem coord, lines, stops;

    public fenetreAccueil() {
        super();

        build();//On initialise notre fenêtre
    }

    public static void main(String[] args) throws IOException {
    	
        fenetreAccueil f = new fenetreAccueil();

        chrono.demarrer();

        log.ecrireConsole("Initialisation de la carte...");
        AreaMap map = new AreaMap(mapLarg, mapHaut);

        log.ecrireConsole("Initialisation de l'heuristique...");
        AStarHeuristic heuristic = new ClosestHeuristic();

        log.ecrireConsole("Initialisation de l'algorithme...");
        pathFinder = new AStar(map, heuristic);

        log.ecrireConsole("Calcul du chemin le plus court...");

        while (typeItineraire != 1 && typeItineraire != 2) {
            typeItineraire = log.poserQuestionOneTwo("Voulez-vous privilégier :\n -Le distance ? (1)\n -Le nombre de changements ? (2)");
            if (typeItineraire == 1) {
                pathFinder.calcShortestPath(depaX, depaY, destX, destY);
            } else if (typeItineraire == 2) {
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
        setTitle("Itinéraire"); //On donne un titre à l'application
        setSize(320, 240); //On donne une taille à notre fenêtre
        setLocationRelativeTo(null); //On centre la fenêtre sur l'écran
        setResizable(false); //On interdit la redimensionnement de la fenêtre
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //On dit à l'application de se fermer lors du clic sur la croix
        setLayout(new BorderLayout());
        
		// ----------------------- MENU BAR --------------------
        barreMenu = new JMenuBar();
		barreMenu.setBackground(Color.white);
		setJMenuBar(barreMenu);
		// Differents choix de la barre de menu
		maj = new JMenu("Mises a jour...");
		barreMenu.add(maj);
		
		coord = new JMenuItem("Charger le fichier coordonnees.csv dans la BDD");
		coord.addActionListener(this);
		maj.add(coord);
		
		stops = new JMenuItem("Charger le fichier stops.csv dans la BDD");
		stops.addActionListener(this);
		maj.add(stops);
		
		lines = new JMenuItem("Charger le fichier lines.csv dans la BDD");
		lines.addActionListener(this);
		maj.add(lines);
		// -----------------------------------------------------
		
        JPanel chemin = new JPanel();
        chemin.setLayout(new BorderLayout());
        JPanel choix = new JPanel();
        JLabel l = new JLabel("Mode de transport");
        choix.add(l);
        final JCheckBox metro = new JCheckBox("Métro");
        metro.setSelected(false);

        final JCheckBox tram = new JCheckBox("Tram");
        tram.setSelected(false);

        final JCheckBox rer = new JCheckBox("RER");
        rer.setSelected(false);

        final JCheckBox all = new JCheckBox("All");
        all.setSelected(true);
        choix.add(metro);
        choix.add(rer);
        choix.add(tram);
        choix.add(all);
        chemin.add(choix, BorderLayout.NORTH);

        JPanel comment = new JPanel();
        final ButtonGroup group = new ButtonGroup();


        final JRadioButton rapide = new JRadioButton("Plus rapide");
        rapide.setSelected(false);
        comment.add(rapide);

        final JRadioButton changement = new JRadioButton("Moins de changements");
        changement.setSelected(false);
        comment.add(changement);

        group.add(rapide);
        group.add(changement);

        chemin.add(comment, BorderLayout.SOUTH);

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
            chemin.add(listeDep, BorderLayout.EAST);
            chemin.add(listeArr, BorderLayout.WEST);

        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
        add(chemin, BorderLayout.NORTH);
        JButton valider = new JButton("Valider");
        valider.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (rapide.isSelected()) {
                            pathFinder.calcShortestPath(depaX, depaY, destX, destY);
                        }
                        if (changement.isSelected()) {
                            pathFinder.calcCoolestPath(depaX, depaY, destX, destY);

                        }
                        chrono.arreter();
                        log.ecrireConsole("Durée de calcul: " + chrono.getDureeMilliSec() + "ms");

                        log.ecrireConsole("\nRésultat:");
                        //pathFinder.printPath();
                        pathFinder.printItineraire();
                       /* if (all.isSelected()) {
                            System.out.println("all");
                        } else {
                            boolean metroB = metro.isSelected();
                            boolean tramB = tram.isSelected();
                            boolean rerB = rer.isSelected();

                            System.out.println(metroB + " " + rerB + " " + tramB);
                        }*/
                    }
                }
        );
        add(valider, BorderLayout.SOUTH);
        pack();
        setVisible(true);
    }
    
	public void actionPerformed(ActionEvent e)
	{
		
		Object source=e.getSource();
		
		if (source==coord)
		{
			MAJCoordonneesBDD();
		}
		else if (source==stops)
		{
			System.out.println("A configurer.");
		}
		else if (source==lines)
		{
			System.out.println("A configurer.");
		}

		
	}
	
	private void MAJCoordonneesBDD()
	{
        try{
        Scrapper.MAJCoordonnees();
        }
        catch (Exception err){}
	}

}
