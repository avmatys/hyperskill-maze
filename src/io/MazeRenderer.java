package maze.io;

import maze.core.Maze;
import maze.core.Point;

import java.util.List;

public interface MazeRenderer {
    void render(Maze maze);
    void render(Maze maze, List<Point> path);
}
