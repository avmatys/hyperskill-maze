package maze.command;

import maze.core.Maze;
import maze.game.GameContext;
import maze.game.GameController;


class GenerateMazeCommand implements Command {

    private final GameContext ctx;
    private final GameController ctrl;

    public GenerateMazeCommand(GameContext ctx, GameController ctrl) {
        this.ctx = ctx;
        this.ctrl = ctrl;
    }

    public void execute() {
        ctx.out.println("Enter the size of a new maze");
        String line = ctx.in.nextLine().trim();
        int size = 0;
        try {
            size = Integer.parseInt(line);
        } catch (NumberFormatException e){
            size = -1;
        }
        if (size <= 0) {
            ctx.out.println("Invalid size. Please try again");
            return;
        }
        Maze maze = ctx.generator.generate(size, size);
        ctx.renderer.render(maze);
        ctrl.setMaze(maze);
    }

}
