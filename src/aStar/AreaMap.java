package aStar;

import aStar.utils.Console;

import java.util.ArrayList;
import java.util.HashMap;

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
                log.ecrireConsole("\tCarte créée");
                registerEdges();
                log.ecrireConsole("\tStations ajoutées");
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
        	//     - Aller chercher dans la base de données
        	//     - Ouvrir et lire les fichiers csv ou txt


            HashMap<Integer, HashMap<Integer, Station>> maMap = new HashMap<Integer, HashMap<Integer, Station>>();
            HashMap<Integer, Station> colQuatre = new HashMap<Integer, Station>();
//            for(Station s: stations){
//                if(map.get(s.getX()) == null)
//                    map.put(s.getX(), new HashMap<s.getY(), s>);
//                else {
//                    HashMap<Integer, Station> hs = map.get(s.getX());
//                    hs.put(s.getY(), s);
//                    map.put(s.getX(), hs)
//
//                }
//            }
            Station station1 = new Station(4, 9, "Hotel de ville");
            station1.nomLign = "Ligne 3";
            colQuatre.put(9, station1);

            Station station2 = new Station(4, 17, "Invalides");
            station2.nomLign = "Ligne 4";
            colQuatre.put(17, station2);
            maMap.put(4, colQuatre);

            HashMap<Integer, Station> colNeuf = new HashMap<Integer, Station>();
            Station station3 = new Station(9, 17, "Europe");
            station3.nomLign = "Ligne 4";
            colNeuf.put(17, station3);
            maMap.put(9, colNeuf);

            Station station4 = new Station(11, 4, "Louis Blanc");
            station4.nomLign = "Ligne 2";
            HashMap<Integer, Station> colOnze = new HashMap<Integer, Station>();
            colOnze.put(4, station4);

            Station station5 = new Station(11, 9, "Belleville");
            station5.nomLign = "Ligne 2";
            colOnze.put(9, station5);
            maMap.put(11, colOnze);

            Station station6 = new Station(13, 14, "Barbés");
            station6.nomLign = "Ligne 2";
            HashMap<Integer, Station> colTreize = new HashMap<Integer, Station>();
            colTreize.put(14, station6);


            Station station7 = new Station(13, 24, "Trocadéro");
            station7.nomLign = "Ligne 1";
            colTreize.put(24, station7);
            maMap.put(13, colTreize);

            Station station8 = new Station(16, 13, "Pyramides");
            station8.nomLign = "Ligne 4";
            HashMap<Integer, Station> colSeize = new HashMap<Integer, Station>();
            colSeize.put(13, station8);
            maMap.put(16, colSeize);

            Station station9 = new Station(17, 17, "Alexandre Dumas");
            station8.nomLign = "Ligne 2";
            HashMap<Integer, Station> colDixSept = new HashMap<Integer, Station>();
            colDixSept.put(17, station9);
            maMap.put(17, colDixSept);


            Station station10 = new Station(19, 22, "Issy");
            station10.nomLign = "Ligne 1";
            HashMap<Integer, Station> colDixNeuf = new HashMap<Integer, Station>();
            colDixNeuf.put(22, station10);
            maMap.put(19, colDixNeuf);

            Station station11 = new Station(21, 4, "Brochant");
            station11.nomLign = "Ligne 1";
            HashMap<Integer, Station> col21 = new HashMap<Integer, Station>();
            col21.put(4, station11);

            Station station12 = new Station(21, 9, "Pyrénées");
            station12.nomLign = "Ligne 3";
            col21.put(9, station12);

            Station station13 = new Station(21, 13, "Cadet");
            station13.nomLign = "Ligne 1";
            col21.put(13, station13);

            Station station14 = new Station(21, 17, "Billancourt");
            station14.nomLign = "Ligne 2";
            col21.put(17, station14);
            maMap.put(21, col21);


            Station station15 = new Station(24, 17, "Couronnes");
            station15.nomLign = "Ligne 2";


            station1.addNeighborAtList(station5);
            station2.addNeighborAtList(station3);
            station3.addNeighborAtList(station6);
            station3.addNeighborAtList(station2);
            station4.addNeighborAtList(station5);
            station5.addNeighborAtList(station4);
            station5.addNeighborAtList(station12);
            station5.addNeighborAtList(station6);
            station5.addNeighborAtList(station1);
            station6.addNeighborAtList(station8);
            station6.addNeighborAtList(station9);
            station6.addNeighborAtList(station3);
            station6.addNeighborAtList(station5);
            station7.addNeighborAtList(station10);
            station8.addNeighborAtList(station13);
            station8.addNeighborAtList(station7);
            station9.addNeighborAtList(station14);
            station9.addNeighborAtList(station6);
            station10.addNeighborAtList(station14);
            station10.addNeighborAtList(station7);
            station11.addNeighborAtList(map.get(21).get(19));
            station12.addNeighborAtList(station11);
            station12.addNeighborAtList(map.get(31).get(9));
            station12.addNeighborAtList(station13);
            station12.addNeighborAtList(station5);
            station13.addNeighborAtList(station12);
            station13.addNeighborAtList(map.get(28).get(13));
            station13.addNeighborAtList(station14);
            station13.addNeighborAtList(station8);
            station14.addNeighborAtList(station13);
            station14.addNeighborAtList(map.get(24).get(17));
            station14.addNeighborAtList(station10);
            station14.addNeighborAtList(station9);
            station15.addNeighborAtList(map.get(28).get(19));
            station15.addNeighborAtList(station14);

            station = map.get(28).get(13);
            station.nomStat = "Saint-Ouen";
        	station.nomLign = "Ligne 4";
            station.addNeighborAtList(map.get(34).get(13));
            station.addNeighborAtList(map.get(21).get(13));

            station = map.get(28).get(19);
            station.nomStat = "La Chapelle";
        	station.nomLign = "Ligne 2";
            station.addNeighborAtList(map.get(32).get(21));
            station.addNeighborAtList(map.get(24).get(17));

            station = map.get(31).get(9);
            station.nomStat = "Place des Fétes";
        	station.nomLign = "Ligne 3";
            station.addNeighborAtList(map.get(34).get(6));
            station.addNeighborAtList(map.get(21).get(9));

            station = map.get(32).get(21);
            station.nomStat = "Opéra";
        	station.nomLign = "Ligne 2";
            station.addNeighborAtList(map.get(28).get(19));

            station = map.get(34).get(6);
            station.nomStat = "Mairie des Lilas";
        	station.nomLign = "Ligne 3";
            station.addNeighborAtList(map.get(31).get(9));


            station = map.get(34).get(13);
            station.nomStat = "Clichy";
        	station.nomLign = "Ligne 4";
            station.addNeighborAtList(map.get(28).get(13));


        }
         
        
        public ArrayList<ArrayList<Station>> getNodes() {
                return map;
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
