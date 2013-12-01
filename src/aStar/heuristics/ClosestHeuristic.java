package aStar.heuristics;


/**
 * A heuristic that uses the tile that is closest to the target
 * as the next best tile.
 */
public class ClosestHeuristic implements AStarHeuristic {

        public int getEstimatedDistanceToGoal(int startX, int startY, int goalX, int goalY)
        {         
                int dx = goalX - startX;
                int dy = goalY - startY;
                int result = (int) (dx*dx)+(dy*dy);
                
                return result;
        }

}