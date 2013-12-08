package aStar;

import aStar.utils.Console;
import connect.DatabaseConnect;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

public class AreaMap {

    HashMap<Integer, HashMap<Integer, Station>> maMap = new HashMap<Integer, HashMap<Integer, Station>>();
    HashMap<Integer, Station> maMapByIndex = new HashMap<Integer, Station>();
    private int mapWith;
    private int mapHeight;
    private ArrayList<ArrayList<Station>> map;
    private int startLocationX = 0;
    private int startLocationY = 0;
    private int goalLocationX = 0;
    private int goalLocationY = 0;
    private Console log = new Console();

    public AreaMap(int mapWith, int mapHeight) {
        this.mapWith = mapWith;
        this.mapHeight = mapHeight;

        createMap();
        log.ecrireConsole("\tCarte créée");
        registerEdges();
        log.ecrireConsole("\tStations ajoutées");
    }

    /**
     * Create the map of stations
     */
    private void createMap() {
        DatabaseConnect connexion = new DatabaseConnect();
        connexion.Connexion();
        Station sta;
        maMap.clear();

        ResultSet stations = connexion.Rechercher("SELECT DISTINCT(stop_name), ty.parent_id, stop_x, stop_y, stop_type FROM tb_stops st JOIN tb_stopscoordon co ON (st.parent_station = co.parent_id) JOIN tb_stopstype ty ON (co.parent_id = ty.parent_id) WHERE parent_station IN (select DISTINCT(parent_id) from tb_stopscoordon) ORDER BY stop_name");

        try {
            HashMap<Integer, Station> hm;
            while (stations.next()) {
                sta = new Station(Integer.parseInt(stations.getString("stop_x")), Integer.parseInt(stations.getString("stop_y")), stations.getString("stop_name"));
                maMapByIndex.put(Integer.parseInt(stations.getString("parent_id")), sta);
                if (maMap.get(sta.getX()) == null) {
                    hm = new HashMap<Integer, Station>();

                } else {
                    hm = maMap.get(sta.getX());
                }
                hm.put(sta.getY(), sta);
                maMap.put(sta.getX(), hm);
            }
            ResultSet voisins = connexion.Rechercher("SELECT DISTINCT `tb_stopsneighbors` . * , `tb_stopscoordon`.`stop_x` , `tb_stopscoordon`.`stop_y` FROM tb_stopsneighbors LEFT JOIN `journey`.`tb_stopscoordon` ON `tb_stopsneighbors`.`stop_id` = `tb_stopscoordon`.`parent_id` ORDER BY stop_id");

            Station s;
            while (voisins.next()) {
                if (voisins.getString("stop_x") != null && voisins.getString("stop_y") != null) {
                    System.out.println(voisins.getString("stop_y"));
                    hm = maMap.get(Integer.parseInt(voisins.getString("stop_x")));
                    if (hm != null && hm.get(Integer.parseInt(voisins.getString("stop_y"))) != null) {
                        s = hm.get(Integer.parseInt(voisins.getString("stop_y")));
                        s.addNeighborAtList(maMapByIndex.get(Integer.parseInt(voisins.getString("stop_neighbor"))));

                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        connexion.Deconnexion();

    }

    /**
     * Ajout des stations et des connexions entre les stations
     */
    private void registerEdges() {


        HashMap<Integer, Station> colQuatre = new HashMap<Integer, Station>();

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
        station9.nomLign = "Ligne 2";
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
        HashMap<Integer, Station> col24 = new HashMap<Integer, Station>();
        col24.put(17, station15);
        maMap.put(24, col24);

        Station station16 = new Station(28, 13, "Saint-Ouen");
        station16.nomLign = "Ligne 4";
        HashMap<Integer, Station> col28 = new HashMap<Integer, Station>();
        col28.put(13, station16);

        Station station17 = new Station(28, 19, "La Chapelle");
        station17.nomLign = "Ligne 2";
        col28.put(19, station17);
        maMap.put(28, col28);

        Station station18 = new Station(31, 9, "Place des Fétes");
        station18.nomLign = "Ligne 3";
        HashMap<Integer, Station> col31 = new HashMap<Integer, Station>();
        col31.put(9, station18);
        maMap.put(31, col31);

        Station station19 = new Station(32, 21, "Opéra");
        station19.nomLign = "Ligne 2";
        HashMap<Integer, Station> col32 = new HashMap<Integer, Station>();
        col32.put(21, station19);
        maMap.put(32, col32);

        Station station20 = new Station(34, 6, "Mairie des Lilas");
        station20.nomLign = "Ligne 3";
        HashMap<Integer, Station> col34 = new HashMap<Integer, Station>();
        col34.put(6, station20);

        Station station21 = new Station(34, 13, "Clichy");
        station21.nomLign = "Ligne 4";
        col34.put(13, station21);
        maMap.put(34, col34);


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
        station11.addNeighborAtList(station12);
        station12.addNeighborAtList(station11);
        station12.addNeighborAtList(station18);
        station12.addNeighborAtList(station13);
        station12.addNeighborAtList(station5);
        station13.addNeighborAtList(station12);
        station13.addNeighborAtList(station16);
        station13.addNeighborAtList(station14);
        station13.addNeighborAtList(station8);
        station14.addNeighborAtList(station13);
        station14.addNeighborAtList(station15);
        station14.addNeighborAtList(station10);
        station14.addNeighborAtList(station9);
        station15.addNeighborAtList(station17);
        station15.addNeighborAtList(station14);
        station16.addNeighborAtList(station21);
        station16.addNeighborAtList(station13);
        station17.addNeighborAtList(station19);
        station17.addNeighborAtList(station15);
        station18.addNeighborAtList(station20);
        station18.addNeighborAtList(station12);
        station19.addNeighborAtList(station17);
        station20.addNeighborAtList(station18);
        station21.addNeighborAtList(station16);


    }

    public ArrayList<ArrayList<Station>> getNodes() {
        return map;
    }

    public Station getNode(int x, int y) {
//                return map.get(x).get(y);
        return maMap.get(x).get(y);
    }

    public void setStartLocation(int x, int y) {
        maMap.get(x).get(y).setStart(true);
        startLocationX = x;
        startLocationY = y;
    }

    public void setGoalLocation(int x, int y) {
        maMap.get(x).get(y).setGoal(true);
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
//                return map.get(startLocationX).get(startLocationY);
        return maMap.get(startLocationX).get(startLocationY);
    }

    public int getGoalLocationX() {
        return goalLocationX;
    }

    public int getGoalLocationY() {
        return goalLocationY;
    }

    public Station getGoalLocation() {
//                return map.get(goalLocationX).get(goalLocationY);
        return maMap.get(goalLocationX).get(goalLocationY);
    }

    public int getDistanceBetween(Station node1, Station node2) {
        //if the nodes are on top or next to each other, return 1
        if (node1.getX() == node2.getX() || node1.getY() == node2.getY()) {
            return 1 * (mapHeight + mapWith);
        } else { //if they are diagonal to each other return diagonal distance: sqrt(1^2+1^2)
            return (int) 1.7 * (mapHeight + mapWith);
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
