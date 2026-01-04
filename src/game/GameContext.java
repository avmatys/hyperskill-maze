package maze.game;

import maze.core.Maze;
import maze.generator.MazeGenerator;
import maze.generator.PrimMazeGenerator;
import maze.io.MazeRenderer;
import maze.io.OutMazeRenderer;
import maze.solver.DijkstraMazeSolver;
import maze.solver.MazeSolver;

import java.io.PrintStream;
import java.io.PrintWriter;
import java.util.Scanner;

public class GameContext {

    public boolean run = true;
    public final MazeGenerator generator;
    public final MazeRenderer renderer;
    public final MazeSolver solver;
    public final Scanner in;
    public final PrintStream out;

    public GameContext(Scanner in, PrintStream out) {
        this.in = in;
        this.out = out;
        this.generator = new PrimMazeGenerator();
        this.renderer = new OutMazeRenderer(this);
        this.solver = new DijkstraMazeSolver();
    }

}
