package views;

import aStar.AStar;
import aStar.AreaMap;
import aStar.Station;
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
import java.sql.SQLException;
import java.util.*;

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
    private static JPanel panelPreferences, panelItineraire, panelValider;
    private static JComboBox comboBoxDepart, comboBoxArrivee;
    private static JButton valider, refresh;
    private static DatabaseConnect BDD;
    private static JRadioButton rapide, changement;
    private static JCheckBox metro, tram, rer, all;
    private static ButtonGroup groupBoutRadio;
    static HashMap<String, Color> couleurs = new HashMap<String, Color>();

    public fenetreAccueil() {
        super();
        build();
    }

    public static void main(String[] args) throws IOException {
        // --- INITIALISATION --- //
        log.ecrireConsole("Initialisation de la carte...");
        AreaMap map = new AreaMap(mapLarg, mapHaut);

        log.ecrireConsole("Initialisation de l'heuristique...");
        AStarHeuristic heuristic = new ClosestHeuristic();

        log.ecrireConsole("Initialisation de l'algorithme...");
        pathFinder = new AStar(map, heuristic);
        // --- FIN INITIALISATION --- //

        // --- AFFICHAGE FENETRE ACCUEIL --- //
        fenetreAccueil f = new fenetreAccueil();
        log.ecrireConsole("Pret.");

    }

    private void build() {
        setTitle("Metro Parisien"); //On donne un titre à l'application
        setSize(1000, 500); //On donne une taille à notre fenêtre
        setResizable(false); //On interdit la redimensionnement de la fenêtre
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //On dit à l'application de se fermer lors du clic sur la croix
        setLayout(new BorderLayout());

        // Barre de menu (haut de l'interface)
        MenuBar();

        // ---------------- PANEL DU HAUT ---------------- //

        panelPreferences = new JPanel();
        panelPreferences.setBackground(new Color(238, 220, 130));
        panelPreferences.setLayout(new BorderLayout());

        JPanel choix = new JPanel();
        choix.setOpaque(false);
        JLabel titreModeTransport = new JLabel("Mode de transport");
        choix.add(titreModeTransport);
        metro = new JCheckBox("Metro");
        metro.setOpaque(false);
        metro.setSelected(false);

        tram = new JCheckBox("Tram");
        tram.setOpaque(false);
        tram.setSelected(false);

        rer = new JCheckBox("RER");
        rer.setOpaque(false);
        rer.setSelected(false);

        all = new JCheckBox("All");
        all.setOpaque(false);
        all.setSelected(true);


        choix.add(metro);
        choix.add(rer);
        choix.add(tram);
        choix.add(all);


        // -------------------------------------- //

        JPanel choix2 = new JPanel();
        choix2.setOpaque(false);
        refresh = new JButton("Rafraichir la liste des stations");
        refresh.addActionListener(this);
        choix2.add(refresh);

        // -------------------------------------- //
        
        Dimension size = new Dimension(100, 20);
        
    	comboBoxDepart = new JComboBox();
    	comboBoxDepart.setOpaque(false);
    	comboBoxDepart.addItem("Veuillez selectionner une station dans la liste");
        comboBoxDepart.setEnabled(false);
        comboBoxDepart.setPrototypeDisplayValue(size);
        
        comboBoxArrivee = new JComboBox();
        comboBoxArrivee.setOpaque(false);
        comboBoxArrivee.addItem("Veuillez selectionner une station dans la liste");
        comboBoxArrivee.setEnabled(false);
        comboBoxArrivee.setPrototypeDisplayValue(size);
     
        
        panelPreferences.add(comboBoxDepart, BorderLayout.WEST);
        panelPreferences.add(comboBoxArrivee, BorderLayout.EAST);
        
        panelPreferences.setMaximumSize(choix2.getPreferredSize() );
       
        // -------------------------------------- //
       
        
        JPanel choix3 = new JPanel();
        choix3.setOpaque(false);

        groupBoutRadio = new ButtonGroup();

        rapide = new JRadioButton("Plus rapide");
        rapide.setOpaque(false);
        rapide.setSelected(true);

        changement = new JRadioButton("Moins de changements");
        changement.setOpaque(false);
        changement.setSelected(false);

        groupBoutRadio.add(changement);
        groupBoutRadio.add(rapide);

        choix3.add(rapide);
        choix3.add(changement);

        panelPreferences.add(choix, BorderLayout.NORTH);
        panelPreferences.add(choix2, BorderLayout.CENTER);
        panelPreferences.add(choix3, BorderLayout.SOUTH);


        // ---------------- PANEL DU CENTRE ---------------- //

        panelItineraire = new JPanel();
        panelItineraire.setBackground(new Color(255, 236, 139));
        panelItineraire.setLayout(new BorderLayout());

        String[] selections = {"Ici, le détail de l'itinéraire", "Ici, le détail de l'itinéraire", "Ici, le détail de l'itinéraire", "Ici, le détail de l'itinéraire"};
        JList list = new JList(selections);
        list.setSelectedIndex(1);
        list.isDisplayable();
        panelItineraire.add(new JScrollPane(list));


        // ---------------- PANEL DU BAS ---------------- //        

        panelValider = new JPanel();
        panelValider.setBackground(new Color(238, 220, 130));
        valider = new JButton("Calculer l'itinéraire");
        valider.addActionListener(this);
        panelValider.add(valider, BorderLayout.CENTER);


        // Ajout des differents panels a la fenetre
        add(panelPreferences, BorderLayout.NORTH);
        add(panelItineraire, BorderLayout.CENTER);
        add(panelValider, BorderLayout.SOUTH);

        setLocationRelativeTo(null); //On centre la fenêtre sur l'ecran
        setVisible(true); // On affiche la fenetre a l'ecran
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
		else if (source==valider)
		{
			chrono.demarrer();
			
	        if (rapide.isSelected()) {
	        	pathFinder.calcShortestPath(depaX, depaY, destX, destY);
	            chrono.arreter();
	            log.ecrireConsole("Durée de calcul: " + chrono.getDureeMilliSec() + "ms");

    public void actionPerformed(ActionEvent e) {

        Object source = e.getSource();

        if (source == coord) {
            MAJCoordonneesBDD();
        } else if (source == stops) {
            MAJStopsBDD();
        } else if (source == lines) {
            MAJLinesBDD();
        } else if (source == valider) {
            chrono.demarrer();

            if (rapide.isSelected()) {
                pathFinder.calcShortestPath(depaX, depaY, destX, destY);
                chrono.arreter();
                log.ecrireConsole("Durée de calcul: " + chrono.getDureeMilliSec() + "ms");

                log.ecrireConsole("\nRésultat:");
                pathFinder.printPath();
                ArrayList<Station> itineraire = pathFinder.getItineraire();
                String[] text = new String[itineraire.size()];
                for (int i = 0; i < itineraire.size(); i++)
                    text[i] = itineraire.get(i).getNomStat() + " [" + itineraire.get(i).getNomLign() + "]";
                JList list = new JList(text);
                list.setCellRenderer(new ColorCellRender());
                panelItineraire.removeAll();
                panelItineraire.add(new JScrollPane(list));
                panelItineraire.revalidate();
                panelItineraire.repaint();


            } else if (changement.isSelected()) {
                pathFinder.calcCoolestPath(depaX, depaY, destX, destY);
                chrono.arreter();
                log.ecrireConsole("Durée de calcul: " + chrono.getDureeMilliSec() + "ms");

                log.ecrireConsole("\nRésultat:");
                pathFinder.printPath();
                pathFinder.printItineraire();
            }
        } else if (source == refresh) {
            comboBoxDepart.setEnabled(true);
            comboBoxArrivee.setEnabled(true);


            if (all.isSelected()) {
                System.out.println("Requete de chargement de tous les arrets confondus");
                Scrapper s = new Scrapper();
                try {
                    comboBoxDepart.removeAllItems();
                    comboBoxArrivee.removeAllItems();
                    s.readStops("SELECT id_stop, name_stop, type_stop FROM tb_stops ORDER BY type_stop, name_stop");
                    Collection<Scrapper.Arret> a = s.getArrs().values();
                    Iterator it = a.iterator();
                    while (it.hasNext()) {
                        Object ar = it.next();
                        comboBoxDepart.addItem(ar.toString());
                        comboBoxArrivee.addItem(ar.toString());
                    }

                } catch (IOException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                } catch (SQLException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }

            }

            if (tram.isSelected() && !metro.isSelected() && !rer.isSelected() && !all.isSelected()) {
                System.out.println("Requete de chargement de tous les arrêts de TRAM");
                Scrapper s = new Scrapper();
                try {
                    comboBoxDepart.removeAllItems();
                    comboBoxArrivee.removeAllItems();
                    s.readStops("SELECT id_stop, name_stop, type_stop FROM tb_stops WHERE type_stop='tram' ORDER BY type_stop, name_stop");
                    Collection<Scrapper.Arret> a = s.getArrs().values();
                    Iterator it = a.iterator();
                    while (it.hasNext()) {
                        Object ar = it.next();
                        comboBoxDepart.addItem(ar.toString());
                        comboBoxArrivee.addItem(ar.toString());
                    }

                } catch (IOException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                } catch (SQLException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
            }

            if (metro.isSelected()) {
                System.out.println("Requete de chargement de tous les arrêts de METRO");
            }

            if (rer.isSelected()) {
                System.out.println("Requete de chargement de tous les arrêts de RER");
            }
        }


    }

    private void MAJCoordonneesBDD() {
        try {
            Scrapper.MAJCoordonnees();
        } catch (Exception err) {
        }
    }

    private void MAJStopsBDD() {
        try {
            Scrapper.MAJStops();
        } catch (Exception err) {
        }
    }

    private void MAJLinesBDD() {
        try {
            Scrapper.MAJLines();
        } catch (Exception err) {
        }
    }

    private void MenuBar() {
        // ------------------------------- MENU BAR -------------------------------
        // --- Barre principale
        barreMenu = new JMenuBar();
        barreMenu.setBackground(new Color(205, 190, 112));
        setJMenuBar(barreMenu);

        // --- Menus
        bdd = new JMenu("Base de donnees");
        barreMenu.add(bdd);

        infos = new JMenu("A propos...");
        barreMenu.add(infos);

        // --- Sous-menus
        maj = new JMenu("Mise a jour");
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

        develop = new JMenuItem("Developpeurs");
        develop.addActionListener(this);
        develop.setEnabled(false); // A supprimer des que fonction developpee
        infos.add(develop);
        // -------------------------------------------------------------------------
    }

    private static class ColorCellRender extends DefaultListCellRenderer {
        public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
            Component c = super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
            Random rand = new Random();
            if (couleurs.get(value.toString().split("\\[")[1].split("]")[0]) == null) {
                float r = rand.nextFloat();
                float g = rand.nextFloat();
                float b = rand.nextFloat();
                couleurs.put(value.toString().split("\\[")[1].split("]")[0], new Color(r, g, b));
            }
            c.setBackground(couleurs.get(value.toString().split("\\[")[1].split("]")[0]));
            return c;
        }
    }
}
