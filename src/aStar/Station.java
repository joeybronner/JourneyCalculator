package aStar;

import java.util.ArrayList;

public class Station implements Comparable<Station>
{
        // Une station
        AreaMap map;
        Station north;
        Station northEast;
        Station east;
        Station southEast;
        Station south;
        Station southWest;
        Station west;
        Station northWest;
        ArrayList<Station> neighborList;
        boolean visited;
        int distanceFromStart;
        int heuristicDistanceFromGoal;
        Station previousNode;
        int x;
        int y;
        boolean isObstacle;
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
                this.isObstacle = false;
                this.isStart = false;
                this.isDestination = false;
                nomStat=nom_stat;
        }
        
        Station (int x, int y, boolean visited, int distanceFromStart, boolean isObstical, boolean isStart, boolean isDestination) {
                neighborList = new ArrayList<Station>();
                this.x = x;
                this.y = y;
                this.visited = visited;
                this.distanceFromStart = distanceFromStart;
                this.isObstacle = isObstical;
                this.isStart = isStart;
                this.isDestination = isDestination;
        }
        
        public Station getNorth() {
                return north;
        }

        public void setNorth(Station north)
        {
                //replace the old Node with the new one in the neighborList
                if (neighborList.contains(this.north))
                        neighborList.remove(this.north);
                neighborList.add(north);
                
                //set the new Node
                this.north = north;
        }

        public Station getNorthEast()
        {
                return northEast;
        }

        public Station getEast()
        {
            return east;
        }
        
        public void setNorthEast(Station northEast)
        {
                //replace the old Node with the new one in the neighborList
                if (neighborList.contains(this.northEast))
                        neighborList.remove(this.northEast);
                neighborList.add(northEast);
                
                //set the new Node
                this.northEast = northEast;
        }

        public void setEast(Station east)
        {
                //replace the old Node with the new one in the neighborList
                if (neighborList.contains(this.east))
                        neighborList.remove(this.east);
                neighborList.add(east);
                
                //set the new Node
                this.east = east;
        }

        public Station getSouthEast() {
                return southEast;
        }

        public void setSouthEast(Station southEast) {
                //replace the old Node with the new one in the neighborList
                if (neighborList.contains(this.southEast))
                        neighborList.remove(this.southEast);
                neighborList.add(southEast);
                
                //set the new Node
                this.southEast = southEast;
        }

        public Station getSouth() {
                return south;
        }

        public void setSouth(Station south) {
                //replace the old Node with the new one in the neighborList
                if (neighborList.contains(this.south))
                        neighborList.remove(this.south);
                neighborList.add(south);
                
                //set the new Node
                this.south = south;
        }

        public Station getSouthWest() {
                return southWest;
        }

        public void setSouthWest(Station southWest) {
                //replace the old Node with the new one in the neighborList
                if (neighborList.contains(this.southWest))
                        neighborList.remove(this.southWest);
                neighborList.add(southWest);
                
                //set the new Node
                this.southWest = southWest;
        }

        public Station getWest() {
                return west;
        }

        public void setWest(Station west) {
                //replace the old Node with the new one in the neighborList
                if (neighborList.contains(this.west))
                        neighborList.remove(this.west);
                neighborList.add(west);
                
                //set the new Node
                this.west = west;
        }

        public Station getNorthWest() {
                return northWest;
        }

        public void setNorthWest(Station northWest) {
                //replace the old Node with the new one in the neighborList
                if (neighborList.contains(this.northWest))
                        neighborList.remove(this.northWest);
                neighborList.add(northWest);
                
                //set the new Node
                this.northWest = northWest;
        }
        
        public ArrayList<Station> getNeighborList() {
                return neighborList;
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
        
        public boolean isObstical() {
                return isObstacle;
        }

        public void setObstical(boolean isObstical) {
                this.isObstacle = isObstical;
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

    public String getNomStat() {
        return nomStat;
    }

    public void setNomStat(String nomStat) {
        this.nomStat = nomStat;
    }

    public String getNomLign() {
        return nomLign;
    }

    public void setNomLign(String nomLign) {
        this.nomLign = nomLign;
    }
}