import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Backjoon7576 {
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static int n, m;
    static int[][] a;
    static Queue<int[]> q = new LinkedList<>();
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        m = Integer.parseInt(st.nextToken());   // 상자의 가로 칸의 수
        n = Integer.parseInt(st.nextToken());   // 상자의 세로 칸의 수
        a = new int[n][m];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(bf.readLine());
            for (int k = 0; k < m; k++) {
                a[i][k]=Integer.parseInt(st.nextToken()); 
                if (a[i][k]==1) {
                    q.add(new int[]{i,k});
                }
            }
        }
        System.out.println(bfs());
    }

    private static int bfs(){
        while (!q.isEmpty()) {
            int[] t = q.poll();
            int x = t[0];
            int y = t[1];
            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];
                if (nx < 0 || nx >= n || ny < 0 || ny >= m) continue;
                if (a[nx][ny] == 0) {
                    a[nx][ny] = a[x][y] + 1;
                    q.add(new int[]{nx, ny});
                }
            }
        }

        int max = Integer.MIN_VALUE;
        if (checkZero()) {
            return -1;
        } else {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    if (max < a[i][j]) {
                        max = a[i][j];
                    }
                }
            }

            return max - 1;
        }
    }

    private static boolean checkZero() {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (a[i][j] == 0)
                    return true;
            }
        }
        return false;
    }
}
