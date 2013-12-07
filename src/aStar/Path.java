package aStar;

import java.util.ArrayList;

public class Path {
    private ArrayList<Station> waypoints = new ArrayList<Station>();

    /**
     * default constructor
     */
    public Path() {
    }

    /**
     * @return size length of the path
     */
    public int getLength() {
        return waypoints.size();
    }

    /**
     * @param index
     * @return station at index
     */
    public Station getWayPoint(int index) {
        return waypoints.get(index);
    }

    /**
     * @param index
     * @return The x coordinate at the waypoint at index.
     */
    public int getX(int index) {
        return getWayPoint(index).getX();
    }

    /**
     * @param index
     * @return The y coordinate at the waypoint at the given index.
     */
    public int getY(int index) {
        return getWayPoint(index).getY();
    }

    /**
     * Add station to the path.
     *
     * @param n
     */
    public void appendWayPoint(Station n) {
        waypoints.add(n);
    }

    /**
     * Add station to the beginning of the path.
     *
     * @param n
     */
    public void prependWayPoint(Station n) {
        waypoints.add(0, n);
    }

    /**
     * Check if this path contains the WayPoint
     *
     * @param x
     * @param y
     * @return True if the path contains the waypoint.
     */
    public boolean contains(int x, int y) {
        for (Station node : waypoints) {
            if (node.getX() == x && node.getY() == y)
                return true;
        }
        return false;
    }

}