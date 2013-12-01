package aStar;

import java.util.ArrayList;

import aStar.utils.Console;

public class AreaMap {

        private int mapWith;
        private int mapHeight;
        private ArrayList<ArrayList<Station>> map;
        private int startLocationX = 0;
        private int startLocationY = 0;
        private int goalLocationX = 0;
        private int goalLocationY = 0;

        private Console log = new Console();
        
        public AreaMap(int mapWith, int mapHeight) 
        {
                this.mapWith = mapWith;
                this.mapHeight = mapHeight;
                
                createMap();
                log.ecrireConsole("\tCarte cr��e");
                registerEdges();
                log.ecrireConsole("\tStations ajout�es");
        }
        private void createMap()
        {
             Station station;             
             
             map = new ArrayList<ArrayList<Station>>();               
                for (int x=0; x<mapWith; x++)
                {
                    map.add(new ArrayList<Station>());
                    for (int y=0; y<mapHeight; y++)
                    {
                            station = new Station(x,y,"Station_"+y);
                            map.get(x).add(station);
                    }
                }
        }

        /**
         * Ajout des stations et des connexions entre les stations
         */
        private void registerEdges()
        {        
        	
        	// Ici, va falloir, soit : 
        	//     - Aller chercher dans la base de donn�es
        	//     - Ouvrir et lire les fichiers csv ou txt
        	
        	Station station;
    	
        	station = map.get(4).get(9);
        	station.nomStat = "Hotel de ville";
        	station.nomLign = "Ligne 3";
        	//station.setNorth(map.get(1).get(1));
        	//station.setNorthEast(map.get(1).get(1));
        	station.setEast(map.get(11).get(9));
        	//station.setSouthEast(map.get(1).get(1));
        	//station.setSouth(map.get(1).get(1));
        	//station.setSouthWest(map.get(1).get(1));
        	//station.setWest(map.get(1).get(1));
        	//station.setNorthWest(map.get(1).get(1));
        	
        	station = map.get(4).get(17);
        	station.nomStat = "Invalides";
        	station.nomLign = "Ligne 4";
        	//station.setNorth(map.get(1).get(1));
        	//station.setNorthEast(map.get(1).get(1));
        	station.setEast(map.get(9).get(17));
        	//station.setSouthEast(map.get(1).get(1));
        	//station.setSouth(map.get(1).get(1));
        	//station.setSouthWest(map.get(1).get(1));
        	//station.setWest(map.get(1).get(1));
        	//station.setNorthWest(map.get(1).get(1));
        	
        	station = map.get(9).get(17);
        	station.nomStat = "Europe";
        	station.nomLign = "Ligne 4";
        	//station.setNorth(map.get(1).get(1));
        	station.setNorthEast(map.get(13).get(14));
        	//station.setEast(map.get(1).get(1));
        	//station.setSouthEast(map.get(1).get(1));
        	//station.setSouth(map.get(1).get(1));
        	//station.setSouthWest(map.get(1).get(1));
        	station.setWest(map.get(4).get(17));
        	//station.setNorthWest(map.get(1).get(1));
        	
        	station = map.get(11).get(4);
        	station.nomStat = "Louis Blanc";
        	station.nomLign = "Ligne 2";
        	//station.setNorth(map.get(1).get(1));
        	//station.setNorthEast(map.get(1).get(1));
        	//station.setEast(map.get(1).get(1));
        	//station.setSouthEast(map.get(1).get(1));
        	station.setSouth(map.get(11).get(9));
        	//station.setSouthWest(map.get(1).get(1));
        	//station.setWest(map.get(1).get(1));
        	//station.setNorthWest(map.get(1).get(1));
        	
        	station = map.get(11).get(9);
        	station.nomStat = "Belleville";
        	station.nomLign = "Ligne 2";
        	station.setNorth(map.get(11).get(4));
        	//station.setNorthEast(map.get(1).get(1));
        	station.setEast(map.get(21).get(9));
        	station.setSouthEast(map.get(13).get(14));
        	//station.setSouth(map.get(1).get(1));
        	//station.setSouthWest(map.get(1).get(1));
        	station.setWest(map.get(4).get(9));
        	//station.setNorthWest(map.get(1).get(1));
        	
        	
        	station = map.get(13).get(14);
        	station.nomStat = "Barb�s";
        	station.nomLign = "Ligne 2";
        	//station.setNorth(map.get(1).get(1));
        	station.setNorthEast(map.get(16).get(13));
        	//station.setEast(map.get(1).get(1));
        	station.setSouthEast(map.get(17).get(17));
        	//station.setSouth(map.get(1).get(1));
        	station.setSouthWest(map.get(9).get(17));
        	//station.setWest(map.get(1).get(1));
        	station.setNorthWest(map.get(11).get(9));
        	
        	station = map.get(13).get(24);
        	station.nomStat = "Trocad�ro";
        	station.nomLign = "Ligne 1";
        	//station.setNorth(map.get(1).get(1));
        	station.setNorthEast(map.get(19).get(22));
        	//station.setEast(map.get(1).get(1));
        	//station.setSouthEast(map.get(1).get(1));
        	//station.setSouth(map.get(1).get(1));
        	//station.setSouthWest(map.get(1).get(1));
        	//station.setWest(map.get(1).get(1));
        	//station.setNorthWest(map.get(1).get(1));
        	
        	station = map.get(16).get(13);
        	station.nomStat = "Pyramides";
        	station.nomLign = "Ligne 4";
        	//station.setNorth(map.get(1).get(1));
        	//station.setNorthEast(map.get(1).get(1));
        	station.setEast(map.get(21).get(13));
        	//station.setSouthEast(map.get(1).get(1));
        	//station.setSouth(map.get(1).get(1));
        	station.setSouthWest(map.get(13).get(14));
        	//station.setWest(map.get(1).get(1));
        	//station.setNorthWest(map.get(1).get(1));
        	
        	station = map.get(17).get(17);
        	station.nomStat = "Alexandre Dumas";
        	station.nomLign = "Ligne 2";
        	//station.setNorth(map.get(1).get(1));
        	//station.setNorthEast(map.get(1).get(1));
        	station.setEast(map.get(21).get(17));
        	//station.setSouthEast(map.get(1).get(1));
        	//station.setSouth(map.get(1).get(1));
        	//station.setSouthWest(map.get(1).get(1));
        	//station.setWest(map.get(1).get(1));
        	station.setNorthWest(map.get(13).get(14));
        	
        	station = map.get(19).get(22);
        	station.nomStat = "Issy";
        	station.nomLign = "Ligne 1";
        	//station.setNorth(map.get(1).get(1));
        	station.setNorthEast(map.get(21).get(17));
        	//station.setEast(map.get(1).get(1));
        	//station.setSouthEast(map.get(1).get(1));
        	//station.setSouth(map.get(1).get(1));
        	station.setSouthWest(map.get(13).get(24));
        	//station.setWest(map.get(1).get(1));
        	//station.setNorthWest(map.get(1).get(1));
        
        	station = map.get(21).get(4);
        	station.nomStat = "Brochant";
        	station.nomLign = "Ligne 1";
        	//station.setNorth(map.get(1).get(1));
        	//station.setNorthEast(map.get(1).get(1));
        	//station.setEast(map.get(1).get(1));
        	//station.setSouthEast(map.get(1).get(1));
        	station.setSouth(map.get(21).get(19));
        	//station.setSouthWest(map.get(1).get(1));
        	//station.setWest(map.get(1).get(1));
        	//station.setNorthWest(map.get(1).get(1));
        	
        	station = map.get(21).get(9);
        	station.nomStat = "Pyr�n�es";
        	station.nomLign = "Ligne 3";
        	station.setNorth(map.get(21).get(4));
        	//station.setNorthEast(map.get(1).get(1));
        	station.setEast(map.get(31).get(9));
        	//station.setSouthEast(map.get(1).get(1));
        	station.setSouth(map.get(21).get(13));
        	//station.setSouthWest(map.get(1).get(1));
        	station.setWest(map.get(11).get(9));
        	//station.setNorthWest(map.get(1).get(1));
        	
        	station = map.get(21).get(13);
        	station.nomStat = "Cadet";
        	station.nomLign = "Ligne 1";
        	station.setNorth(map.get(21).get(9));
        	//station.setNorthEast(map.get(1).get(1));
        	station.setEast(map.get(28).get(13));
        	//station.setSouthEast(map.get(1).get(1));
        	station.setSouth(map.get(21).get(17));
        	//station.setSouthWest(map.get(1).get(1));
        	station.setWest(map.get(16).get(13));
        	//station.setNorthWest(map.get(1).get(1));
        	
        	station = map.get(21).get(17);
        	station.nomStat = "Billancourt";
        	station.nomLign = "Ligne 2";
        	station.setNorth(map.get(21).get(13));
        	//station.setNorthEast(map.get(1).get(1));
        	station.setEast(map.get(24).get(17));
        	//station.setSouthEast(map.get(1).get(1));
        	//station.setSouth(map.get(1).get(1));
        	station.setSouthWest(map.get(19).get(22));
        	station.setWest(map.get(17).get(17));
        	//station.setNorthWest(map.get(1).get(1));
        	
        	station = map.get(24).get(17);
        	station.nomStat = "Couronnes";
        	station.nomLign = "Ligne 2";
        	//station.setNorth(map.get(1).get(1));
        	//station.setNorthEast(map.get(1).get(1));
        	//station.setEast(map.get(1).get(1));
        	station.setSouthEast(map.get(28).get(19));
        	//station.setSouth(map.get(1).get(1));
        	//station.setSouthWest(map.get(1).get(1));
        	station.setWest(map.get(21).get(17));
        	//station.setNorthWest(map.get(1).get(1));
        	
        	station = map.get(28).get(13);
        	station.nomStat = "Saint-Ouen";
        	station.nomLign = "Ligne 4";
        	//station.setNorth(map.get(1).get(1));
        	//station.setNorthEast(map.get(1).get(1));
        	station.setEast(map.get(34).get(13));
        	//station.setSouthEast(map.get(1).get(1));
        	//station.setSouth(map.get(1).get(1));
        	//station.setSouthWest(map.get(1).get(1));
        	station.setWest(map.get(21).get(13));
        	//station.setNorthWest(map.get(1).get(1));
        	
        	station = map.get(28).get(19);
        	station.nomStat = "La Chapelle";
        	station.nomLign = "Ligne 2";
        	//station.setNorth(map.get(1).get(1));
        	//station.setNorthEast(map.get(1).get(1));
        	//station.setEast(map.get(1).get(1));
        	station.setSouthEast(map.get(32).get(21));
        	//station.setSouth(map.get(1).get(1));
        	//station.setSouthWest(map.get(1).get(1));
        	//station.setWest(map.get(1).get(1));
        	station.setNorthWest(map.get(24).get(17));
        	
        	station = map.get(31).get(9);
        	station.nomStat = "Place des F�tes";
        	station.nomLign = "Ligne 3";
        	//station.setNorth(map.get(1).get(1));
        	station.setNorthEast(map.get(34).get(6));
        	//station.setEast(map.get(1).get(1));
        	//station.setSouthEast(map.get(1).get(1));
        	//station.setSouth(map.get(1).get(1));
        	//station.setSouthWest(map.get(1).get(1));
        	station.setWest(map.get(21).get(9));
        	//station.setNorthWest(map.get(1).get(1));
        	
        	station = map.get(32).get(21);
        	station.nomStat = "Op�ra";
        	station.nomLign = "Ligne 2";
        	//station.setNorth(map.get(1).get(1));
        	//station.setNorthEast(map.get(1).get(1));
        	//station.setEast(map.get(1).get(1));
        	//station.setSouthEast(map.get(1).get(1));
        	//station.setSouth(map.get(1).get(1));
        	//station.setSouthWest(map.get(1).get(1));
        	//station.setWest(map.get(1).get(1));
        	station.setNorthWest(map.get(28).get(19));
        	
        	station = map.get(34).get(6);
        	station.nomStat = "Mairie des Lilas";
        	station.nomLign = "Ligne 3";
        	//station.setNorth(map.get(1).get(1));
        	//station.setNorthEast(map.get(1).get(1));
        	//station.setEast(map.get(1).get(1));
        	//station.setSouthEast(map.get(1).get(1));
        	//station.setSouth(map.get(1).get(1));
        	station.setSouthWest(map.get(31).get(9));
        	//station.setWest(map.get(1).get(1));
        	//station.setNorthWest(map.get(1).get(1));
        	
        	station = map.get(34).get(13);
        	station.nomStat = "Clichy";
        	station.nomLign = "Ligne 4";
        	//station.setNorth(map.get(1).get(1));
        	//station.setNorthEast(map.get(1).get(1));
        	//station.setEast(map.get(1).get(1));
        	//station.setSouthEast(map.get(1).get(1));
        	//station.setSouth(map.get(1).get(1));
        	//station.setSouthWest(map.get(31).get(9));
        	station.setWest(map.get(28).get(13));
        	//station.setNorthWest(map.get(1).get(1));
        	
        	//station = map.get(1).get(1);
        	//station.nomStat = "Nom";
        	//station.setNorth(map.get(1).get(1));
        	//station.setNorthEast(map.get(1).get(1));
        	//station.setEast(map.get(1).get(1));
        	//station.setSouthEast(map.get(1).get(1));
        	//station.setSouth(map.get(1).get(1));
        	//station.setSouthWest(map.get(1).get(1));
        	//station.setWest(map.get(1).get(1));
        	//station.setNorthWest(map.get(1).get(1));
            
        }
         
        
        public ArrayList<ArrayList<Station>> getNodes() {
                return map;
        }
        public void setObstical(int x, int y, boolean isObstical) {
                map.get(x).get(y).setObstical(isObstical);
        }

        public Station getNode(int x, int y) {
                return map.get(x).get(y);
        }

        public void setStartLocation(int x, int y) {
                map.get(startLocationX).get(startLocationY).setStart(false);
                map.get(x).get(y).setStart(true);
                startLocationX = x;
                startLocationY = y;
        }

        public void setGoalLocation(int x, int y) {
                map.get(goalLocationX).get(goalLocationY).setGoal(false);
                map.get(x).get(y).setGoal(true);
                goalLocationX = x;
                goalLocationY = y;
        }

        public int getStartLocationX() {
                return startLocationX;
        }

        public int getStartLocationY() {
                return startLocationY;
        }
        
        public Station getStartNode() {
                return map.get(startLocationX).get(startLocationY);
        }

        public int getGoalLocationX() {
                return goalLocationX;
        }

        public int getGoalLocationY() {
                return goalLocationY;
        }
        
        public Station getGoalLocation() {
                return map.get(goalLocationX).get(goalLocationY);
        }
        
        public int getDistanceBetween(Station node1, Station node2) {
                //if the nodes are on top or next to each other, return 1
                if (node1.getX() == node2.getX() || node1.getY() == node2.getY()){
                        return 1*(mapHeight+mapWith);
                } else { //if they are diagonal to each other return diagonal distance: sqrt(1^2+1^2)
                        return (int) 1.7*(mapHeight+mapWith);
                }
        }
        
        public int getMapWith() {
                return mapWith;
        }
        public int getMapHeight() {
                return mapHeight;
        }
        public void clear() {
                startLocationX = 0;
                startLocationY = 0;
                goalLocationX = 0;
                goalLocationY = 0;
                createMap();
                registerEdges();
        }
}