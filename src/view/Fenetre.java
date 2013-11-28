package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;

public class Fenetre extends JFrame {

    public Fenetre() {
        super();

        build();//On initialise notre fenêtre
    }

    public static void main(String[] args) {
        Fenetre f = new Fenetre();
    }

    private void build() {
        setTitle("Itinéraire"); //On donne un titre à l'application
        setSize(320, 240); //On donne une taille à notre fenêtre
        setLocationRelativeTo(null); //On centre la fenêtre sur l'écran
        setResizable(false); //On interdit la redimensionnement de la fenêtre
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //On dit à l'application de se fermer lors du clic sur la croix
        setLayout(new GridLayout());
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
        add(choix);
        setVisible(true);
    }

}
