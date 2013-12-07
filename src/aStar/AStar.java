package aStar;

import java.util.ArrayList;
import java.util.Collections;

import aStar.heuristics.AStarHeuristic;
import aStar.utils.Console;

public class AStar {
        private AreaMap map;
        private AStarHeuristic heuristic;

        /**
         * closedList The list of Nodes not searched yet, sorted by their distance to the goal as guessed by our heuristic.
         */
        private ArrayList<Station> closedList;
        private SortedNodeList openList;
        private SortedNodeList rechercheList;
        private ArrayList <Station> cheminList;
        private Path shortestPath;
        Console log = new Console();

        public AStar(AreaMap map, AStarHeuristic heuristic)
        {
                this.map = map;
                this.heuristic = heuristic;

                closedList = new ArrayList<Station>();
                openList = new SortedNodeList();
                cheminList = new ArrayList<Station>();
                rechercheList = new SortedNodeList();
                }

        public Path calcShortestPath(int startX, int startY, int goalX, int goalY)
        {

                //mark start and goal node
                map.setStartLocation(startX, startY);
                map.setGoalLocation(goalX, goalY);

                map.getStartNode().setDistanceFromStart(0);
                closedList.clear();
                openList.clear();
                openList.add(map.getStartNode());

                //while we haven't reached the goal yet
                while(openList.size() != 0) {

                        //get the first Node from non-searched Node list, sorted by lowest distance from our goal as guessed by our heuristic
                        Station current = openList.getFirst();

                        // check if our current Node location is the goal Node. If it is, we are done.
                        if(current.getX() == map.getGoalLocationX() && current.getY() == map.getGoalLocationY()) {
                                return reconstructPath(current);
                        }

                        //move current Node to the closed (already searched) list
                        openList.remove(current);
                        closedList.add(current);

                        //go through all the current Nodes neighbors and calculate if one should be our next step
                        for(Station neighbor : current.getNeighborList()) {
                                boolean neighborIsBetter;

                                //if we have already searched this Node, don't bother and continue to the next one 
                                if (closedList.contains(neighbor))
                                        continue;

                                        // calculate how long the path is if we choose this neighbor as the next step in the path 
                                        int neighborDistanceFromStart = (current.getDistanceFromStart() + map.getDistanceBetween(current, neighbor));

                                        //add neighbor to the open list if it is not there
                                        if(!openList.contains(neighbor)) {
                                                openList.add(neighbor);
                                                neighborIsBetter = true;
                                                //if neighbor is closer to start it could also be better
                                        } else if(neighborDistanceFromStart < current.getDistanceFromStart())
                                        {
                                                neighborIsBetter = true;
                                        } else {
                                                neighborIsBetter = false;
                                        }
                                        // set neighbors parameters if it is better
                                        if (neighborIsBetter) {
                                                neighbor.setPreviousNode(current);
                                                neighbor.setDistanceFromStart(neighborDistanceFromStart);
                                                neighbor.setHeuristicDistanceFromGoal(heuristic.getEstimatedDistanceToGoal(neighbor.getX(), neighbor.getY(), map.getGoalLocationX(), map.getGoalLocationY()));
                                        }
                                

                        }

                }
                return null;
        }

        public Path calcCoolestPath(int startX, int startY, int goalX, int goalY) {

            // Station de début et de fin
            map.setStartLocation(startX, startY);
            map.setGoalLocation(goalX, goalY);
            
            // On initialise le point de départ qui sera le ZERO
            map.getStartNode().setDistanceFromStart(0);
            
            // On vide les différentes listes
            cheminList.clear();
            rechercheList.clear();
            
            // On crée la liste des point é ouvrir
            rechercheList.add(map.getStartNode());

            // Tant qu'il n'est pas arrivé jusqu'au point d'arrivée

            
            // -------------------------------
            //    JUSQU'ICI, NE RIEN TOUCHER (un seul passage!)
            // -------------------------------
            
            while(rechercheList.size() != 0)
            {

                    //get the first Node from non-searched Node list, sorted by lowest distance from our goal as guessed by our heuristic
                    Station current = rechercheList.getFirst();

                    // Si la station actuelle est la destination, alors on a terminé
                    if(current.getX() == map.getGoalLocationX() && current.getY() == map.getGoalLocationY())
                    {
                    	// On renvoi simplement l'itinéraire (déjé construit) dans l'ordre.
                    	return reconstructPath(current);
                    }

                    // Au premier tour on supprimer le départ pour l'ajouter é closedList qui contient l'tineraire
                    rechercheList.remove(current);
                    cheminList.add(current);


                    // Récupération de la liste des voisins pour le point actuel
                    for(Station neighbor : current.getNeighborList())
                    {
                            boolean neighborIsBetter;
                            
                            //if we have already searched this Node, don't bother and continue to the next one 
                            if (cheminList.contains(neighbor))
                            {
                            	continue;
                            }
                            else
                            {

                                // calculate how long the path is if we choose this neighbor as the next step in the path 
                                int neighborDistanceFromStart = (current.getDistanceFromStart() + map.getDistanceBetween(current, neighbor));
                                
                                boolean sameLine;
                                if (current.nomLign == neighbor.nomLign)
                                	sameLine=true;
                                else
                                	sameLine=false;
                                
                                //add neighbor to the open list if it is not there
                                if(!rechercheList.contains(neighbor))
                                {
                                	rechercheList.add(neighbor);
                                    neighborIsBetter = true;
                                }
                                else if(neighborDistanceFromStart < current.getDistanceFromStart())
                                {
                                    neighborIsBetter = true; 
                                }
                                else
                                {
                                    neighborIsBetter = false;
                                }
                                
                                
                                // Si le voisin est mieux, on le remplace
                                
                                if (neighborIsBetter)
                                {
                                	if (!neighbor.getSameLine() && sameLine)
                                	{
                                		neighbor.setPreviousNode(current);
                                        neighbor.setDistanceFromStart(neighborDistanceFromStart);
                                        neighbor.setHeuristicDistanceFromGoal(heuristic.getEstimatedDistanceToGoal(neighbor.getX(), neighbor.getY(), map.getGoalLocationX(), map.getGoalLocationY()));	
                                        neighbor.setSameLine(sameLine);
                                	}
                                }

                            }
                            
                            if (!neighbor.getSameLine())
                            {
                                for(Station neighbor2 : current.getNeighborList())
                                {                                        
                                        //if we have already searched this Node, don't bother and continue to the next one 
                                        if (cheminList.contains(neighbor2))
                                        {
                                        	continue;
                                        }
                                        else
                                        {

                                            // calculate how long the path is if we choose this neighbor as the next step in the path 
                                            int neighborDistanceFromStart = (current.getDistanceFromStart() + map.getDistanceBetween(current, neighbor2));
                                            
                                            boolean sameLine;
                                            if (current.nomLign == neighbor.nomLign)
                                            	sameLine=true;
                                            else
                                            	sameLine=false;
                                            
                                            //add neighbor to the open list if it is not there
                                            if(!rechercheList.contains(neighbor2))
                                            {
                                            	rechercheList.add(neighbor2);
                                                neighborIsBetter = true;
                                            }
                                            else if(neighborDistanceFromStart < current.getDistanceFromStart())
                                            {
                                                neighborIsBetter = true; 
                                            }
                                            else
                                            {
                                                neighborIsBetter = false;
                                            }
                                            
                                            
                                            // Si le voisin est mieux, on le remplace
                                            
                                            if (neighborIsBetter)
                                            {
                                            		neighbor2.setPreviousNode(current);
                                                    neighbor2.setDistanceFromStart(neighborDistanceFromStart);
                                                    neighbor2.setHeuristicDistanceFromGoal(heuristic.getEstimatedDistanceToGoal(neighbor2.getX(), neighbor2.getY(), map.getGoalLocationX(), map.getGoalLocationY()));	
                                                    neighbor2.setSameLine(sameLine);
                                            } 

                                        }
                                }
                            }
                    	}
                    
                   
            }
            
            return null;
        }
        
        public void printPath() {
                Station node;
                
                for(int x=0; x<map.getMapWith(); x++) {

                        if (x==0) {
                                for (int i=0; i<=map.getMapWith(); i++)
                                        System.out.print("_");
                                System.out.println();   
                        }
                        System.out.print("|");

                        for(int y=0; y<map.getMapHeight(); y++) {
                                node = map.getNode(x, y);
                                if (node.isStart) {
                                        System.out.print("s");
                                } else if (node.isDestination) {
                                        System.out.print("g");
                                } else if (shortestPath.contains(node.getX(), node.getY())) {
                                        System.out.print("é");
                                } 
                        		  else {
                                        System.out.print(" ");
                                }
                                if (y==map.getMapHeight())
                                        System.out.print("_");
                        }

                        System.out.print("|");
                        System.out.println();
                }
                for (int i=0; i<=map.getMapWith(); i++)
                {
                        System.out.print("-");
                }
                
        }

        public void printItineraire()
        {
        	int nbChange = 0;
        	String nomOldLign = map.getStartNode().nomLign;
        	System.out.println("\nNombre de stations traversées : " + shortestPath.getLength());
            System.out.println("Départ    : " + map.getStartNode().nomStat + " (" +  map.getStartNode().nomLign + ")");
            for (int i=0;i<shortestPath.getLength();i++)
            {
            	int numStat = i+1;
            	int x = shortestPath.getWayPoint(i).getX();
            	int y = shortestPath.getWayPoint(i).getY();
            	
            	// Check pour voir si il y a changement de ligne
            	if (map.getNode(x, y).nomLign != nomOldLign && nomOldLign != "" && i != shortestPath.getLength()-1)
            	{
            		nbChange++;
            	}
            	nomOldLign = map.getNode(x, y).nomLign;
            	
            	if (i != shortestPath.getLength()-1)
            	{
            		System.out.println("Arrét né" + numStat + " : " + map.getNode(x, y).nomStat + " (" +  map.getNode(x, y).nomLign + ")");
            	}
            	else
            	{
            		System.out.println("Arrivée   : " + map.getNode(x, y).nomStat + " (" +  map.getNode(x, y).nomLign + ")");
            	}
            		
            }
            System.out.println("\nNombre de changements de ligne(s) : " + nbChange);
        }
        
        private Path reconstructPath(Station node) {
                Path path = new Path();
             
                while(!(node.getPreviousNode() == null))
                {
                        path.prependWayPoint(node);
                        node = node.getPreviousNode();
                }
                this.shortestPath = path;
                return path;
        }

        private class SortedNodeList
        {
                private ArrayList<Station> list = new ArrayList<Station>();

                public Station getFirst() {
                        return list.get(0);
                }

                public void clear() {
                        list.clear();
                }

                public void add(Station node) {
                        list.add(node);
                        Collections.sort(list);
                }

                public void remove(Station n) {
                        list.remove(n);
                }

                public int size() {
                        return list.size();
                }

                public boolean contains(Station n) {
                        return list.contains(n);
                }
        }

}