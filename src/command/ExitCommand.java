package maze.command;

import maze.game.GameContext;

public class ExitCommand implements Command {
    
    private GameContext ctx;

    public ExitCommand(GameContext ctx) {
        this.ctx = ctx;
    }

    public void execute() {
        ctx.out.println("Bye!");
        ctx.run = false;
    }

}
