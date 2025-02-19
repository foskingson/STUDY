package hackerRank;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Bfs {
    public static List<Integer> bfs(int n, int m, List<List<Integer>> edges, int s) {
        List<Integer>[] graph = new ArrayList[n+1];
        boolean[] visited = new boolean[n+1];
        int[] res = new int[n+1];

        for (int i = 0; i <= n; i++) {
            graph[i] = new ArrayList<>();
        }

        for (List<Integer> edge : edges) {
            int a = edge.get(0);
            int b = edge.get(1);
            graph[a].add(b);
            graph[b].add(a);
        }

        Queue<Integer> q = new LinkedList<>();
        q.add(s);
        visited[s] = true;
        res[s] = 0; 

        while (!q.isEmpty()) {
            int v = q.poll();
            for (int u : graph[v]) {
                if (!visited[u]) {
                    q.add(u);
                    visited[u] = true;
                    res[u] = res[v] + 6; 
                }
            }
        }

        List<Integer> resList = new ArrayList<>();
        for (int i = 1; i <= n; i++) {
            if (i != s) { 
                if (res[i]==0) {
                    resList.add(-1);
                    continue;
                }
                resList.add(res[i]);
            }
        }

        return resList;
    }
}
