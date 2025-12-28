package maze.game;

import maze.io.MazeRenderer;

public class GameController {

    private final Game game;
    private final MazeRenderer renderer;

    public GameController(Game game, MazeRenderer renderer){
        this.game = game;
        this.renderer = renderer;
    }

    public void play() {
        this.game.start();
        this.renderer.render(this.game.getMaze());
    }
}
