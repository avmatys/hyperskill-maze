package maze.io;

import maze.core.CellType;
import maze.core.Maze;
import maze.game.GameContext;

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

    private String symbolOf(CellType cell) {
        return switch (cell) {
            case WALL  -> "\u2588\u2588";
            case PATH -> "  ";
            default -> "?";
        };
    }
}
