package maze.command;

import maze.core.Maze;
import maze.game.GameContext;
import maze.game.GameController;

class DisplayMazeCommand implements Command {

    private final GameContext ctx;
    private final GameController ctrl;

    public DisplayMazeCommand(GameContext ctx, GameController ctrl) {
        this.ctx = ctx;
        this.ctrl = ctrl;
    }

    public void execute() {
        Maze maze = ctrl.getMaze();
        if (maze == null)
            return;
        ctx.renderer.render(maze);
    }

}
