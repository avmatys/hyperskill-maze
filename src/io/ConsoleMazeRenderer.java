package maze.io;

import maze.core.CellType;
import maze.core.Maze;
import maze.core.Point;

import java.util.List;

public class ConsoleMazeRenderer implements MazeRenderer {

    @Override
    public void render(Maze maze) {
        for (int i = 0; i < maze.getRows(); i++) {
            for (int j = 0; j < maze.getCols(); j++) {
                System.out.print(symbolOf(maze.getCell(i, j)));
            }
            System.out.println();
        }
    }

    @Override
    public void render(Maze maze, List<Point> path) {
        throw new RuntimeException("Not implemented");
    }


    private String symbolOf(CellType cell) {
        return switch (cell) {
            case WALL  -> "\u2588\u2588";
            case PATH -> "  ";
            default -> "??";
        };
    }
}
