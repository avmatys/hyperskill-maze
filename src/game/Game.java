package maze.game;

import maze.core.Maze;
import maze.generator.GeneratorFactory;
import maze.generator.MazeGenerator;

public class Game {

    private final GameSettings settings;
    private Maze maze;
    private final MazeGenerator mazeGenerator;

    public Game(GameSettings settings){
        this.settings = settings;
        this.mazeGenerator = GeneratorFactory.create(settings.getGeneratorType());
    }

    public void start(){
        this.maze = this.mazeGenerator.generate(this.settings.getRows(), this.settings.getCols());
    }

    public Maze getMaze() {
        return new Maze(this.maze);
    }
}
