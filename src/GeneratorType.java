package maze.generator;

public enum GeneratorType {
    PRIM("PRIM");

    private final String generatorType;

    GeneratorType(String generatorType) {
        this.generatorType = generatorType;
    }

    public String generatorType() {
        return generatorType;
    }
}

