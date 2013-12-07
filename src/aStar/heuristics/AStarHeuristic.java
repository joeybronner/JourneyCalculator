package aStar.heuristics;

public interface AStarHeuristic {

        /**
         *
         * The heuristic tries to guess how far a given Node is from the goal Node.
         * The lower the cost, the more likely a Node will be searched next.
         *
         * @param startX The x coordinate of the tile being evaluated
         * @param startY The y coordinate of the tile being evaluated
         * @param goalX The x coordinate of the target location
         * @param goalY The y coordinate of the target location
         * @return The cost associated with the given tile
         */
        public int getEstimatedDistanceToGoal(int startX, int startY, int goalX, int goalY);
}