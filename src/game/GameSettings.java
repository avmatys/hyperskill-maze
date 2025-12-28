package maze.game;

import maze.generator.GeneratorType;
import maze.io.MazeRenderer;

public final class GameSettings {

    private final int rows;
    private final int cols;
    private final GeneratorType generatorType;


    public GameSettings(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
        this.generatorType = GeneratorType.PRIM;
    }
    public GameSettings(int rows, int cols, GeneratorType generatorType) {
        this.rows = rows;
        this.cols = cols;
        this.generatorType = generatorType != null ? generatorType : GeneratorType.PRIM;
    }

    public int getRows() {
        return rows;
    }

    public int getCols() {
        return cols;
    }

    public GeneratorType getGeneratorType() {
        return generatorType;
    }
}
