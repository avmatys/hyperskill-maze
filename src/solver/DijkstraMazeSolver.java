package maze.solver;

import maze.core.Maze;
import maze.core.Point;

import java.util.*;

public class DijkstraMazeSolver implements MazeSolver{

    private final static int[][] NEIS = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

    static class Pair {
         public Point point;
         public int steps;
         public Pair(Point point, int steps){
             this.point = point;
             this.steps = steps;
         }
    }

    private List<Point> getNeighborList(Maze maze, Point point) {
        List<Point> result = new ArrayList<>();
        for (int[] dir : NEIS) {
            Point nei = new Point(point.getRow() + dir[0], point.getCol() + dir[1]);
            if (maze.isInBounds(nei) && maze.isPath(nei)) {
                result.add(nei);
            }
        }
        return result;
    }

    @Override
    public List<Point> solve(Maze maze) {
        List<Point> path = new LinkedList<>();
        if (maze == null || maze.getEntrance() == null || maze.getExit() == null)
            return path;
        // Run Dijkstra to find the shortest path
        Point entrance = maze.getEntrance();
        Map<Point, Point> prev = new HashMap<>();
        prev.put(entrance, null); // This will be used to restore the whole path
        Map<Point, Integer> steps = new HashMap<>();
        steps.put(entrance, 0); // This is used to find the shortest path available
        Queue<Pair> queue = new PriorityQueue<>((p1, p2) -> p1.steps - p2.steps);
        queue.offer(new Pair(entrance, 0));
        while (!queue.isEmpty()) {
            Pair top = queue.poll();
            if (top.steps > steps.getOrDefault(top.point, Integer.MAX_VALUE))
                continue; // Skip this point as it's not better
            steps.put(top.point, top.steps);
            for (Point p: getNeighborList(maze, top.point)) {
                if (top.steps + 1 < steps.getOrDefault(p, Integer.MAX_VALUE)){
                    steps.put(p, top.steps + 1);
                    prev.put(p, top.point); // Store sequence of steps
                    queue.offer(new Pair(p, top.steps + 1));
                }
            }
        }
        Point current = maze.getExit();
        while (current != null) {
            path.add(current);
            current = prev.get(current);
        }
        Collections.reverse(path); // Reverse as we started from the end
        return path;
    }
}
