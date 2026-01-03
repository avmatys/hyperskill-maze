package maze;

import maze.game.GameContext;
import maze.game.GameController;

import java.io.*;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        try (Scanner in = new Scanner(System.in)) {
            GameContext ctx = new GameContext(in, System.out);
            GameController controller = new GameController(ctx);
            controller.play();
        }
    }

}
