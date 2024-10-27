import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Backjoon1707 { // 이분 그래프 판별하기
// 이분 그래프(Bipartite Graph)란
// 인접한 정점끼리 서로 다른 색으로 칠해서 모든 정점을 두 가지 색으로만 칠할 수 있는 그래프.
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new FileReader("txt/Backjoon1707.txt"));
        int n = Integer.parseInt(bf.readLine());
        for (int i = 0; i < n; i++) {   // 테스트 케이스 만큼 반복
            StringTokenizer st = new StringTokenizer(bf.readLine());
            int v = Integer.parseInt(st.nextToken());   // 정점의 개수
            int e = Integer.parseInt(st.nextToken());   // 간선의 개수
            ArrayList<Integer>[] graph = new ArrayList[v+1]; 
            for (int k = 1; k <= v; k++) {
                graph[k]= new ArrayList<>();
            }
            for (int k = 0; k < e; k++) {
                st = new StringTokenizer(bf.readLine());
                int v1 = Integer.parseInt(st.nextToken());   
                int v2 = Integer.parseInt(st.nextToken());   
                graph[v1].add(v2);
                graph[v2].add(v1);
            }
            solution(graph,v);
        }
    }

    private static void solution(ArrayList<Integer>[] graph, int v) {
        int[] visited = new int[graph.length]; // 각 정점을 1,2로 방문 체크
        for (int i = 1; i <= v; i++) { // 모든 연결 성분을 검사
            if (visited[i] == 0) { // 방문하지 않은 정점이라면
                if (!dfs(i, graph, 1, visited)) {
                    System.out.println("NO");
                    return;
                }
            }
        }
        System.out.println("YES");
    }

    private static boolean dfs(int v, ArrayList<Integer>[] graph, int check, int[] visited) {
        visited[v] = check; // 현재 노드를 색칠
        int nextCheck = 3 - check; // 다음 노드는 반대 색으로 설정
        
        for (int u : graph[v]) {
            if (visited[u] == 0) { // 아직 방문하지 않은 노드
                if (!dfs(u, graph, nextCheck, visited)) {
                    return false; // 이분 그래프가 아님
                }
            } else if (visited[u] == check) { // 인접한 노드가 같은 색일 경우
                return false;
            }
        }
        return true;
    }
}
