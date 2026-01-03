package maze.command;

import maze.game.GameContext;
import maze.game.GameController;

public class CommandInitializer {

    public static CommandRegistry initialize(GameContext ctx, GameController ctrl) {
        CommandRegistry registry = new CommandRegistry();
        registry.register("0", new ExitCommand(ctx));
        registry.register("1", new GenerateMazeCommand(ctx, ctrl));
        registry.register("2", new LoadMazeCommand(ctx, ctrl));
        registry.register("3", new SaveMazeCommand(ctx, ctrl));
        registry.register("4", new DisplayMazeCommand(ctx, ctrl));
        return registry;
    }

}
