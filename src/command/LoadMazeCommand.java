package maze.command;

import maze.core.Maze;
import maze.game.GameContext;
import maze.game.GameController;

import java.io.*;

class LoadMazeCommand implements Command {

    private final GameContext ctx;
    private final GameController ctrl;

    public LoadMazeCommand(GameContext ctx, GameController ctrl) {
        this.ctx = ctx;
        this.ctrl = ctrl;
    }

    public void execute() {
        String filename = ctx.in.nextLine().trim();
        if (filename.isEmpty()) {
            ctx.out.println("Incorrect filename. Please try again");
            return;
        }
        File file = new File(filename);
        if (!file.exists() || !file.isFile()) {
           ctx.out.printf("The file %s does not exist", filename);
           return;
        }
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(file))) {
            Object obj = in.readObject();
            if (!(obj instanceof Maze)) {
                ctx.out.println("Cannot load the maze. It has an invalid format");
                return;
            }
            ctrl.setMaze((Maze) obj);
        } catch (IOException e) {
            ctx.out.println("I/O error while loading maze: " + e.getMessage());
        } catch (ClassNotFoundException e) {
            ctx.out.println("Cannot load the maze. It has an invalid format");
        }
    }

}
