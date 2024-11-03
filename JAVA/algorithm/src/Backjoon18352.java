import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Backjoon18352 {
    static boolean[] visited;
    static int[] minDis;
    static ArrayList<Integer> res = new ArrayList<>(); 

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        int n = Integer.parseInt(st.nextToken());   // 도시 개수
        int m = Integer.parseInt(st.nextToken());   // 도로 개수
        int k = Integer.parseInt(st.nextToken());   // 거리 정보
        int x = Integer.parseInt(st.nextToken());   // 출발 도시의 번호
        ArrayList<Integer>[] graph = new ArrayList[n + 1];

        for (int i = 1; i <= n; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 1; i <= m; i++) {
            st = new StringTokenizer(bf.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            graph[a].add(b);
        }

        solution(graph, n, k, x);
    }

    private static void solution(ArrayList<Integer>[] graph, int n, int k, int x) {
        visited = new boolean[n + 1];
        minDis = new int[n + 1];
        
        for (int i = 1; i <= n; i++) {
            minDis[i] = -1;
        }
        bfs(graph, x);

        for (int i = 1; i <= n; i++) {
            if (minDis[i] == k) {
                res.add(i);
            }
        }

        if (res.isEmpty()) {
            System.out.println(-1);
        } else {
            for (int result : res) {
                System.out.println(result);
            }
        }
    }

    private static void bfs(ArrayList<Integer>[] graph, int start) {
        Queue<Integer> q = new LinkedList<>();
        q.add(start);
        visited[start] = true;
        minDis[start] = 0; 

        while (!q.isEmpty()) {
            int v = q.poll();
            for (int u : graph[v]) {
                if (!visited[u]) {
                    visited[u] = true;
                    minDis[u] = minDis[v] + 1; 
                    q.add(u);
                }
            }
        }
    }
}
