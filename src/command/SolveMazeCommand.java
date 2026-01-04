package maze.command;

import maze.core.Maze;
import maze.core.Point;
import maze.game.GameContext;
import maze.game.GameController;

import java.util.List;


class SolveMazeCommand implements Command {

    private final GameContext ctx;
    private final GameController ctrl;

    public SolveMazeCommand(GameContext ctx, GameController ctrl) {
        this.ctx = ctx;
        this.ctrl = ctrl;
    }

    public void execute() {
        Maze maze = ctrl.getMaze();
        if (maze == null)
            return;
        List<Point> path = ctx.solver.solve(maze);
        ctrl.setPath(path);
        ctx.renderer.render(maze, path);
    }

}
