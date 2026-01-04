package maze.io;

import maze.core.CellType;
import maze.core.Maze;
import maze.core.Point;
import maze.game.GameContext;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class OutMazeRenderer implements MazeRenderer {

    private final GameContext ctx;

    public OutMazeRenderer(GameContext ctx) {
        this.ctx = ctx;
    }

    @Override
    public void render(Maze maze) {
        for (int i = 0; i < maze.getRows(); i++) {
            for (int j = 0; j < maze.getCols(); j++) {
                ctx.out.print(symbolOf(maze.getCell(i, j)));
            }
            ctx.out.println();
        }
    }

    @Override
    public void render(Maze maze, List<Point> path) {
        Set<Point> pathPoints = new HashSet<>(path); // Quick check of a specific point
        for (int i = 0; i < maze.getRows(); i++) {
            for (int j = 0; j < maze.getCols(); j++) {
                Point point = new Point(i, j);
                if (pathPoints.contains(point)) {
                    ctx.out.print("//");
                } else {
                    ctx.out.print(symbolOf(maze.getCell(point)));
                }
            }
            ctx.out.println();
        }
    }

    private String symbolOf(CellType cell) {
        return switch (cell) {
            case WALL  -> "\u2588\u2588";
            case PATH -> "  ";
            default -> "??";
        };
    }
}
