package maze.command;

import maze.core.Maze;
import maze.game.GameContext;
import maze.game.GameController;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

class SaveMazeCommand implements Command {

    private final GameContext ctx;
    private final GameController ctrl;

    public SaveMazeCommand(GameContext ctx, GameController ctrl) {
        this.ctx = ctx;
        this.ctrl = ctrl;
    }

    public void execute() {
        String filename = ctx.in.nextLine().trim();
        if (filename.isEmpty()) {
            ctx.out.println("Incorrect filename. Please try again");
            return;
        }
        Maze maze = ctrl.getMaze();
        if (maze == null) {
            ctx.out.println("Maze iis not generated. Please try again later");
            return;
        }
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(filename))) {
            out.writeObject(maze);
            out.flush();
        } catch (IOException e) {
            throw new RuntimeException("Failed to save maze to file: " + filename, e);
        }
    }

}
