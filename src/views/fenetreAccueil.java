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
import java.util.Iterator;

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
    private static JMenuBar barreMenu;
    private static JMenu bdd, maj, infos;
    private static JMenuItem coord, lines, stops, develop;
    private static JPanel depart, arrive, chemin;
    private static JComboBox listeDep, listeArr;
    private static DatabaseConnect BDD;

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
        setResizable(false); //On interdit la redimensionnement de la fenêtre
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //On dit à l'application de se fermer lors du clic sur la croix
        setLayout(new BorderLayout());

    	
		// ------------------------------- MENU BAR -------------------------------
        // --- Barre principale
        barreMenu = new JMenuBar();
		barreMenu.setBackground(Color.LIGHT_GRAY);
		setJMenuBar(barreMenu);
		
		// --- Menus
		bdd = new JMenu("Base de données");
		barreMenu.add(bdd);
		
		infos = new JMenu("A propos...");
		barreMenu.add(infos);
		
		// --- Sous-menus
		maj = new JMenu("Mise à jour");
		bdd.add(maj);
		
		coord = new JMenuItem("coordonnees.csv...");
		coord.addActionListener(this);
		maj.add(coord);
		
		stops = new JMenuItem("stops.csv...");
		stops.addActionListener(this);
		maj.add(stops);
		
		lines = new JMenuItem("lines.csv...");
		lines.addActionListener(this);
		maj.add(lines);
		
		develop = new JMenuItem("Développeurs");
		develop.addActionListener(this);
		develop.setEnabled(false); // A supprimer des que fonction developpee
		infos.add(develop);
		// -------------------------------------------------------------------------
        
		depart = new JPanel();
        arrive = new JPanel();
		
        chemin = new JPanel();
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
        
        JButton refresh = new JButton("Rafraichir la liste des stations");
        refresh.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                    	listeDep.setEnabled(true);
                    	//listeDep.removeAllItems();
                    	listeArr.setEnabled(true);
                    	//listeArr.removeAllItems();
                    	                  	
                        
                        if (all.isSelected()) {
                        	System.out.println("Requete de chargement de tous les arrêts confondus");
                        }
                        
                        if (tram.isSelected()) {
                        	System.out.println("Requete de chargement de tous les arrêts de TRAM");
                        }
                        
                        if (metro.isSelected()) {
                        	System.out.println("Requete de chargement de tous les arrêts de METRO");
                        }
                        
                        if (rer.isSelected()) {
                        	System.out.println("Requete de chargement de tous les arrêts de RER");
                        }
                        
                    }
                }
        );
        
        choix.add(metro);
        choix.add(rer);
        choix.add(tram);
        choix.add(all);
        choix.add(refresh, BorderLayout.NORTH);
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

        Scrapper s = new Scrapper();
        try {
            s.readStops();
            Collection<Scrapper.Arret> a = s.getArrs().values();
            listeDep = new JComboBox(a.toArray());
            listeArr = new JComboBox(a.toArray());
            listeDep.setEnabled(false);
            listeArr.setEnabled(false);

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
        setLocationRelativeTo(null); //On centre la fenêtre sur l'écran
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
			MAJStopsBDD();
		}
		else if (source==lines)
		{
			MAJLinesBDD();
		}

		
	}
	
	private void MAJCoordonneesBDD()
	{
        try{
        Scrapper.MAJCoordonnees();
        }
        catch (Exception err){}
	}
	
	private void MAJStopsBDD()
	{
        try{
        Scrapper.MAJStops();
        }
        catch (Exception err){}
	}
	
	private void MAJLinesBDD()
	{
        try{
        Scrapper.MAJLines();
        }
        catch (Exception err){}
	}

}
