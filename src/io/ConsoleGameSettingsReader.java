package maze.io;

import java.util.InputMismatchException;
import java.util.Scanner;

import maze.game.GameSettings;

public class ConsoleGameSettingsReader implements GameSettingsReader {

    private final Scanner scanner;

    public ConsoleGameSettingsReader(Scanner scanner) {
        this.scanner = scanner;
    }

    @Override
    public GameSettings read() {
        while (true) {
            System.out.println("Please, enter the size of a maze");
            String line = scanner.nextLine().trim();
            try {
                String[] parts = line.split("\\s+");
                if (parts.length != 2) {
                    throw new IllegalArgumentException("Expected two numbers");
                }
                int rows = Integer.parseInt(parts[0]);
                int cols = Integer.parseInt(parts[1]);
                if (rows <= 0 || cols <= 0) {
                    throw new IllegalArgumentException("Values must be positive");
                }
                return new GameSettings(rows, cols);
            } catch (IllegalArgumentException e) {
                System.out.println("Invalid input. Please enter two positive integers, e.g. 7 9");
            }
        }
    }
}
