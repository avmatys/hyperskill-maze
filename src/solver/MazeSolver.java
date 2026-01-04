package maze.solver;

import maze.core.Maze;
import maze.core.Point;

import java.util.List;

public interface MazeSolver {
    public List<Point> solve(Maze maze);
}
