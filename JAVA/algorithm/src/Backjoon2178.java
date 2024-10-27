
import java.awt.Point;
import java.util.*;

public class Backjoon2178 {
    // [단계 1] 받아 줄 값 세팅하기.
    static int n, m;    // n*m 미로
    static int[][] arr; // 미로를 담아줄 배열
    static boolean[][] visited; // 방문 여부 확인
    static int[] dx = {-1, 1, 0, 0};  // x 방향 상하좌우
    static int[] dy = {0, 0, -1, 1}; // y방향 상하좌우

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        m = sc.nextInt();
        arr = new int[n][m];
        visited = new boolean[n][m];
        sc.nextLine(); // buffer 비워주기
        
        // 배열에 값 담아주기
        for (int i = 0; i < n; i++) {
            String line = sc.next();    // 값을 한 줄로 받아옴
            for (int j = 0; j < m; j++) {
                arr[i][j] = line.charAt(j) - '0'; // 아스키코드 정수로 변환
            }
        }
        // [단계 1] 종료.

        // [단계 3] bfs 알고리즘에 시작 위치 넣어주기.
        bfs(0, 0);   // 시작위치
        // [단계 3] 종료.

        // [단계 4] 값 출력해주기.
        // bfs 를 거치고 나면 도착할 칸의 행렬의 값은 거쳐야 할 최소 칸의 수가 되므로
        System.out.println(arr[n - 1][m - 1]);  // 마지막 칸의 인덱스로 값을 출력해주기.
        // [단계 4] 종료.
    }
    // [단계 2] bfs 알고리즘 짜기
    static void bfs(int x, int y) {
        // Queue 생성
        Queue<Point> queue = new LinkedList<>();
        queue.offer(new Point(x, y));
        visited[x][y] = true;   // 방문처리

        while (!queue.isEmpty()) {
            Point currentPoint = queue.poll();
            // 현재 위치에서 사방위 이동가능여부 판별해주기
            for (int i = 0; i < 4; i++) {
                int nextX = currentPoint.x + dx[i];
                int nextY = currentPoint.y + dy[i];

                // 1. 범위 이내에 있는가?
                if (nextX < 0 || nextX >= n || nextY < 0 || nextY >= m)
                    continue;   // 범위에서 나가면 제끼기
                // 2. 막힌 길인가?
                if (arr[nextX][nextY] == 0)
                    continue;   // 막힌 길이면 제끼기
                // 3. 이미 방문 했나?
                if (visited[nextX][nextY])
                    continue;   // 방문했으면(true 이면) 제끼기
                // 모든 조건에 해당되지 않음. 즉, 갈 수 있는 길 이면
                // queue 에 삽입해주고 방문처리.
                queue.offer(new Point(nextX, nextY));
                visited[nextX][nextY] = true;
                // 그리고 최소 칸의 수를 구해야되므로 해당하는 칸에 1씩 누적해서
                // 도착 칸이면 그 값이 지나야 하는 최소 칸의 수.
                arr[nextX][nextY] = arr[currentPoint.x][currentPoint.y] + 1;
            }
        }
    }
    // [단계 2] 종료.
}