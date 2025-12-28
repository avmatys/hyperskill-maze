package maze.generator;

import maze.core.*;

import java.util.*;

public class PrimMazeGenerator implements MazeGenerator {

    private final Random random = new Random();
    private final static int[][] DIRS = {{0, 2}, {0, -2}, {2, 0}, {-2, 0}};
    private final static int[][] NEIS = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
    private final static int MAX_RETRY = 5;

    private Maze maze;
    private int rows;
    private int cols;
    private int retryCount;

    @Override
    public Maze generate(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
        this.maze = new Maze(rows, cols);
        int startRow = random.nextInt(rows / 2) * 2 + 1;
        int startCol = random.nextInt(cols / 2) * 2 + 1;
        Point start = new Point(startRow, startCol);
        this.maze.makePath(start);
        List<Point> frontierList = getFrontierList(start);
        while (!frontierList.isEmpty()) {
            int frontierIdx = random.nextInt(frontierList.size());
            Point frontier = frontierList.get(frontierIdx);
            frontierList.set(frontierIdx, frontierList.getLast());
            frontierList.removeLast();
            List<Point> pathList = getNeighborList(frontier);
            if (!pathList.isEmpty()) {
                int pathIdx = random.nextInt(pathList.size());
                Point path = pathList.get(pathIdx);
                Point between = new Point((frontier.getRow() + path.getRow()) / 2, (frontier.getCol() + path.getCol()) / 2);
                this.maze.makePath(frontier);
                this.maze.makePath(between);
                for (Point nextFrontier : this.getFrontierList(frontier)) {
                    if (frontierList.contains(nextFrontier)) continue;
                    frontierList.add(nextFrontier);
                }
            }
        }
        // Enhance the maze with enty and exit
        if (!this.addEntranceAndExit()) {
            if (retryCount < MAX_RETRY) {
                this.retryCount += 1;
                this.generate(this.rows, this.cols);
            } else {
                throw new RuntimeException("Can't generate a correct maze");
            }
        }
        this.retryCount = 0; // We can reset this val
        return this.maze;
    }

    private List<Point> getNeighborList(Point point) {
        List<Point> result = new ArrayList<>();
        for (int[] dir : DIRS) {
            Point nei = new Point(point.getRow() + dir[0], point.getCol() + dir[1]);
            if (this.maze.isInBounds(nei) && this.maze.isPath(nei)) {
                result.add(nei);
            }
        }
        return result;
    }

    private List<Point> getFrontierList(Point point) {
        List<Point> result = new ArrayList<>();
        for (int[] dir : DIRS) {
            Point frontier = new Point(point.getRow() + dir[0], point.getCol() + dir[1]);
            if (this.maze.isInBounds(frontier) && !this.maze.isOnBorder(frontier) && this.maze.isWall(frontier)) {
                result.add(frontier);
            }
        }
        return result;
    }

    private boolean addEntranceAndExit() {
        // Select any path point in the maze
        Point start = null;
        do {
            int startRow = random.nextInt(rows / 2) * 2 + 1;
            int startCol = random.nextInt(cols / 2) * 2 + 1;
            start = new Point(startRow, startCol);
        } while (!this.maze.isPath(start));
        // Run the bfs to find possible entry and exit
        Deque<Point> queue = new ArrayDeque<>();
        Set<Point> seen = new HashSet<>();
        List<Point> possibleEntrance = new ArrayList<>();
        queue.add(start);
        while (!queue.isEmpty()) {
            Point current = queue.poll();
            if (seen.contains(current))
                continue;
            seen.add(current);
            for (int[] nei : NEIS) {
                Point next = new Point(current.getRow() + nei[0],
                        current.getCol() + nei[1]);
                if (!this.maze.isInBounds(next)) continue;
                if (this.maze.isPath(next)){
                    queue.add(next);
                }
                if (this.maze.isOnBorder(next)){
                    possibleEntrance.add(next);
                }
            }
        }
        // Select any 2 points on the border
        int m = possibleEntrance.size();
        if (m < 2) {
            return false;
        }
        int first = this.random.nextInt(m);
        int second = first;
        int count = 0;
        while ((first == second || possibleEntrance.get(first).getRow() == possibleEntrance.get(second).getRow()
               || possibleEntrance.get(first).getCol() == possibleEntrance.get(second).getCol()) && count < MAX_RETRY) {
            second = this.random.nextInt(m);
            count++;
        }
        // Mark them as paths
        this.maze.makePath(possibleEntrance.get(first));
        this.maze.makePath(possibleEntrance.get(second));
        return true;
    }

}
