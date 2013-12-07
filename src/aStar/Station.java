package aStar;

import java.util.ArrayList;

public class Station implements Comparable<Station>
{
        // Une station
        AreaMap map;
//        Station north;
//        Station northEast;
//        Station east;
//        Station southEast;
//        Station south;
//        Station southWest;
//        Station west;
//        Station northWest;
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
        
        Station(int x, int y, String nom_stat)
        {
                neighborList = new ArrayList<Station>();
                this.x = x;
                this.y = y;
                this.visited = false;
                this.distanceFromStart = Integer.MAX_VALUE;
                this.isStart = false;
                this.isDestination = false;
                this.nomStat=nom_stat;
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
        
        public ArrayList<Station> getNeighborList() {
                return neighborList;
        }
        
        public void addNeighborAtList(Station directNeighbor) {
        		neighborList.add(directNeighbor);
        }

        public boolean isVisited() {
                return visited;
        }

        public void setVisited(boolean visited) {
                this.visited = visited;
        }

        public int getDistanceFromStart() {
                return distanceFromStart;
        }

        public void setDistanceFromStart(int f) {
                this.distanceFromStart = f;
        }
        
        public void setMetroLine(String line)
        {
        		this.nomLign = line;
        }
        
        public String getMetroLine(){
        		return this.nomLign;
        }

        public Station getPreviousNode() {
                return previousNode;
        }
        
        public boolean getSameLine(){
        		return this.hasChange;
        }
        
        public void setSameLine(boolean change){
    			this.hasChange = change;
        }

        public void setPreviousNode(Station previousNode) {
                this.previousNode = previousNode;
        }
        
        public int getHeuristicDistanceFromGoal() {
                return heuristicDistanceFromGoal;
        }

        public void setHeuristicDistanceFromGoal(int f) {
                this.heuristicDistanceFromGoal = f;
        }

        public int getX() {
                return x;
        }

        public void setX(int x) {
                this.x = x;
        }

        public int getY() {
                return y;
        }

        public void setY(int y) {
                this.y = y;
        }

        public boolean isStart() {
                return isStart;
        }

        public void setStart(boolean isStart) {
                this.isStart = isStart;
        }

        public boolean isDestination() {
                return isDestination;
        }

        public void setGoal(boolean isDestination) {
                this.isDestination = isDestination;
        }

        public boolean equals(Station node) {
                return (node.x == x) && (node.y == y);
        }

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
        
}