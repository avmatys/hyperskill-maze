package maze.game;

import maze.command.Command;
import maze.command.CommandInitializer;
import maze.command.CommandRegistry;
import maze.core.Maze;

public class GameController {

    private Maze maze;
    private final GameContext ctx;
    private final CommandRegistry registry;

    public GameController(GameContext ctx) {
        this.ctx = ctx;
        this.registry = CommandInitializer.initialize(ctx, this);
    }

    public void setMaze(Maze maze) {
        this.maze = maze;
    }

    public boolean hasMaze() {
        return this.maze != null;
    }

    public Maze getMaze() {
        return this.maze;
    }

    public void play() {
        while (ctx.run) {
            showMenu();
            String opt = ctx.in.nextLine().trim();
            Command command = registry.get(opt);
            if (command == null) {
                ctx.out.println("Incorrect option. Please try again");
                continue;
            }
            command.execute();
            ctx.out.println();
        }
    }

    public void showMenu() {
        ctx.out.println("=== Menu ===");
        ctx.out.println("1. Generate a new maze");
        ctx.out.println("2. Load a maze");
        if (this.hasMaze()) {
            ctx.out.println("3. Save the maze");
            ctx.out.println("4. Display the maze");
        }
        ctx.out.println("0. Exit");
    }

}
