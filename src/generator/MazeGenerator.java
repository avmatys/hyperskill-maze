package maze.generator;

import maze.core.Maze;

public interface MazeGenerator {
    public Maze generate(int rows, int cols);
}
