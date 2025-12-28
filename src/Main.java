package maze;

import maze.core.Maze;
import maze.game.Game;
import maze.game.GameController;
import maze.game.GameSettings;
import maze.generator.GeneratorFactory;
import maze.generator.MazeGenerator;
import maze.io.ConsoleGameSettingsReader;
import maze.io.ConsoleMazeRenderer;
import maze.io.GameSettingsReader;
import maze.io.MazeRenderer;

import java.util.Scanner;

public class Main {

    private static final int WALL = 1;
    private static final int PATH = 0;
    private static final String WALL_SYMBOL = "\u2588\u2588";

    public static void main(String[] args) {
        GameSettings settings = null;
        try (Scanner scanner = new Scanner(System.in)) {
            GameSettingsReader reader = new ConsoleGameSettingsReader(scanner);
            settings = reader.read();
        }
        Game game = new Game(settings);
        MazeRenderer renderer = new ConsoleMazeRenderer();
        GameController controller = new GameController(game, renderer);
        controller.play();
    }

}
