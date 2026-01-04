package maze.core;

import java.io.Serial;
import java.io.Serializable;

public class Maze implements Cloneable, Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private final CellType[][] grid;
    private final int rows;
    private final int cols;
    private Point entrance;
    private Point exit;

    public Maze(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
        this.grid = new CellType[this.rows][this.cols];
        for (int i = 0; i < this.rows; i++) {
            for (int j = 0; j < this.cols; j++) {
                this.grid[i][j] = CellType.WALL;
            }
        }
    }

    public Maze(Maze copy){
        assert copy != null : "Maze copy is null";
        this.rows = copy.rows;
        this.cols = copy.cols;
        this.grid = new CellType[this.rows][this.cols];
        for (int i = 0; i < this.rows; i++) {
            for (int j = 0; j < this.cols; j++) {
                this.grid[i][j] = copy.grid[i][j];
            }
        }
    }

    public CellType getCell(Point point) {
        if (point == null) return CellType.NONE;
        return this.getCell(point.getRow(), point.getCol());
    }

    public CellType getCell(int row, int col) {
        return this.isInBounds(row, col) ? this.grid[row][col] :CellType.NONE;
    }

    public int getRows() {
        return this.rows;
    }

    public int getCols() {
        return this.cols;
    }

    public boolean isWall(int row, int col) {
        return this.isInBounds(row, col) && this.isType(row, col, CellType.WALL);
    }

    public boolean isWall(Point point) {
        if (point == null) return true;
        return this.isWall(point.getRow(), point.getCol());
    }

    public boolean isPath(int row, int col) {
        return this.isInBounds(row, col) && this.isType(row, col, CellType.PATH);
    }

    public boolean isPath(Point point) {
        if (point == null) return false;
        return this.isPath(point.getRow(), point.getCol());
    }

    public void makePath(Point point) {
        if (point == null) return;
        this.set(point, CellType.PATH);
    }

    private boolean isType(int row, int col, CellType type) {
        return this.grid[row][col] == type;
    }

    public boolean isInBounds(int row, int col) {
        return row >= 0 && row < this.rows && col >= 0 && col < this.cols;
    }

    public boolean isInBounds(Point point) {
        return this.isInBounds(point.getRow(), point.getCol());
    }

    public boolean isOnBorder(Point point){
        if (point == null) return false;
        int row = point.getRow(), col = point.getCol();
        return row == 0 || row == this.rows - 1 || col == 0 || col == this.cols - 1;
    }

    private void set(int row, int col, CellType type){
        if (this.isInBounds(row, col)){
            this.grid[row][col] = type;
        }
    }

    private void set(Point point, CellType type){
        if (point == null) return;
        this.set(point.getRow(), point.getCol(), type);
    }

    public void setEntrance(Point point) {
        if (!this.isOnBorder(point)) return;
        this.entrance = point;
    }

    public void setExit(Point point) {
        if (!this.isOnBorder(point)) return;
        this.exit = point;
    }

    public Point getEntrance() {
        return this.entrance;
    }

    public Point getExit() {
        return this.exit;
    }

}
