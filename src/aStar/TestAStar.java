package aStar;

import aStar.heuristics.AStarHeuristic;
import aStar.heuristics.ClosestHeuristic;
import aStar.utils.Compteur;
import aStar.utils.Console;

/**
 * Classe de test du programme
 *
 * @author Joey Bronner
 * @version 1.0
 */

public class TestAStar {

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
    private static int mapLarg = 7;
    private static int mapHaut = 8;
    private static int depaX = 1;
    private static int depaY = 1;
    private static int destX = 6;
    private static int destY = 7;


    public static void main(String[] args) {
        Console log = new Console();
        Compteur chrono = new Compteur();
        chrono.demarrer();

        log.ecrireConsole("Initialisation de la carte...");
        AreaMap map = new AreaMap(mapLarg, mapHaut);

        log.ecrireConsole("Création du réseau...");
        AStarHeuristic heuristic = new ClosestHeuristic();

        log.ecrireConsole("Initialisation de la recherche...");
        AStar pathFinder = new AStar(map, heuristic);

        log.ecrireConsole("Calcul du chemin le plus court...");
        pathFinder.calcShortestPath(depaX, depaY, destX, destY);

        chrono.arreter();
        log.ecrireConsole("Durée de calcul: " + chrono.getDureeMilliSec());

        log.ecrireConsole("Carte:");
        pathFinder.printPath();
    }

}