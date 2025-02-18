package hackerRank;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class MinimumMoves {
    public static int minimumMoves(List<String> grid, int startX, int startY, int goalX, int goalY) {
        int[] dx = new int[]{-1, 0, 1, 0}; 
        int[] dy = new int[]{0, 1, 0, -1};
        boolean[][] visited = new boolean[grid.size()][grid.size()];
        Queue<int[]> q = new LinkedList<>();

        visited[startX][startY] = true;
        q.add(new int[]{startX, startY});
        int count = 0;

        while (!q.isEmpty()) {
            int size = q.size();  
            count++;

            for (int i = 0; i < size; i++) {
                int[] p = q.poll();
                int x = p[0], y = p[1];

                for (int j = 0; j < 4; j++) {
                    int nextX = x;
                    int nextY = y;

                    while (true) {
                        nextX += dx[j];
                        nextY += dy[j];

                        if (nextX < 0 || nextX >= grid.size() || nextY < 0 || nextY >= grid.size() || grid.get(nextX).charAt(nextY) == 'X') {
                            break;  
                        }

                        if (nextX == goalX && nextY == goalY) {
                            return count;  
                        } else if (!visited[nextX][nextY]) {
                            visited[nextX][nextY] = true;
                            q.add(new int[]{nextX, nextY});  
                        }
                    }
                }
            }
        }

        return -1;  
    }
}
