package aStar;

import java.util.ArrayList;

public class Station implements Comparable<Station> {
    // Une station
    AreaMap map;
    ArrayList<Station> neighborList;
    boolean visited;
    int distanceFromStart;
    int heuristicDistanceFromGoal;
    Station previousNode;
    int x;
    int y;
    //        boolean isObstacle;
    boolean isStart;
    boolean isDestination;
    boolean hasChange;
    String nomStat;
    String nomLign;

    /**
     * Station constructor
     *
     * @param x
     * @param y
     * @param nom_stat
     */
    Station(int x, int y, String nom_stat) {
        neighborList = new ArrayList<Station>();
        this.x = x;
        this.y = y;
        this.visited = false;
        this.distanceFromStart = Integer.MAX_VALUE;
        this.isStart = false;
        this.isDestination = false;
        this.nomStat = nom_stat;
    }

//        Station (int x, int y, boolean visited, int distanceFromStart, boolean isObstical, boolean isStart, boolean isDestination) {
//                neighborList = new ArrayList<Station>();
//                this.x = x;
//                this.y = y;
//                this.visited = visited;
//                this.distanceFromStart = distanceFromStart;
//                this.isStart = isStart;
//                this.isDestination = isDestination;
//        }
//       

    /**
     * Neighbors getter
     *
     * @return ArrayList<Station>
     */
    public ArrayList<Station> getNeighborList() {
        return neighborList;
    }

    /**
     * Add Station to neighbors
     *
     * @param directNeighbor
     */
    public void addNeighborAtList(Station directNeighbor) {
        neighborList.add(directNeighbor);
    }

    /**
     * @return true if visited else false
     */
    public boolean isVisited() {
        return visited;
    }

    /**
     * set visited
     *
     * @param visited
     */
    public void setVisited(boolean visited) {
        this.visited = visited;
    }

    /**
     * @return distanceFromStart
     */
    public int getDistanceFromStart() {
        return distanceFromStart;
    }

    /**
     * set distance from start
     *
     * @param f
     */
    public void setDistanceFromStart(int f) {
        this.distanceFromStart = f;
    }

    /**
     * @return name of the line
     */
    public String getMetroLine() {
        return this.nomLign;
    }

    /**
     * set the name of the line
     *
     * @param line
     */
    public void setMetroLine(String line) {
        this.nomLign = line;
    }

    /**
     * @return previous node
     */
    public Station getPreviousNode() {
        return previousNode;
    }

    /**
     * set previous node
     *
     * @param previousNode
     */
    public void setPreviousNode(Station previousNode) {
        this.previousNode = previousNode;
    }

    /**
     * @return true if same line else false
     */
    public boolean getSameLine() {
        return this.hasChange;
    }

    /**
     * set sameline boolean
     *
     * @param change
     */
    public void setSameLine(boolean change) {
        this.hasChange = change;
    }

    /**
     * @return heuristicDistanceFromGoal
     */
    public int getHeuristicDistanceFromGoal() {
        return heuristicDistanceFromGoal;
    }

    /**
     * set heuristicDistanceFromGoal
     *
     * @param f
     */
    public void setHeuristicDistanceFromGoal(int f) {
        this.heuristicDistanceFromGoal = f;
    }

    /**
     * @return x coordinate
     */
    public int getX() {
        return x;
    }

    /**
     * set x coordinate
     *
     * @param x
     */
    public void setX(int x) {
        this.x = x;
    }

    /**
     * @return y coordinate
     */
    public int getY() {
        return y;
    }

    /**
     * set y coordinate
     *
     * @param y
     */
    public void setY(int y) {
        this.y = y;
    }

    /**
     * @return true if is start else false
     */
    public boolean isStart() {
        return isStart;
    }

    /**
     * set start boolean
     *
     * @param isStart
     */
    public void setStart(boolean isStart) {
        this.isStart = isStart;
    }

    /**
     * @return true if is destination else false
     */
    public boolean isDestination() {
        return isDestination;
    }

    /**
     * set destination
     *
     * @param isDestination
     */
    public void setGoal(boolean isDestination) {
        this.isDestination = isDestination;
    }

    /**
     * @param node
     * @return true if same coordinates else false
     */
    public boolean equals(Station node) {
        return (node.x == x) && (node.y == y);
    }

    /**
     * compare 2 nodes
     *
     * @param otherNode
     * @return int
     */
    public int compareTo(Station otherNode) {
        int thisTotalDistanceFromGoal = heuristicDistanceFromGoal + distanceFromStart;
        int otherTotalDistanceFromGoal = otherNode.getHeuristicDistanceFromGoal() + otherNode.getDistanceFromStart();

        if (thisTotalDistanceFromGoal < otherTotalDistanceFromGoal) {
            return -1;
        } else if (thisTotalDistanceFromGoal > otherTotalDistanceFromGoal) {
            return 1;
        } else {
            return 0;
        }
    }

    /**
     * @return name station
     */
    public String getNomStat() {
        return nomStat;
    }

    /**
     * set name station
     *
     * @param nomStat
     */
    public void setNomStat(String nomStat) {
        this.nomStat = nomStat;
    }

    /**
     * @return name line
     */
    public String getNomLign() {
        return nomLign;
    }

    /**
     * set name line
     *
     * @param nomLign
     */
    public void setNomLign(String nomLign) {
        this.nomLign = nomLign;
    }

}