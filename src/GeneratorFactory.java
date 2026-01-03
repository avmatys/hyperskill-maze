package maze.generator;

import java.util.Objects;

public class GeneratorFactory {
    public static MazeGenerator create(GeneratorType type) throws IllegalArgumentException {
        if (Objects.requireNonNull(type) == GeneratorType.PRIM) {
            return new PrimMazeGenerator();
        }
        throw new IllegalArgumentException("Invalid maze generator type");
    }
}
