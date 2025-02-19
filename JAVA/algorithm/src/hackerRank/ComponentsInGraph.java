package hackerRank;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ComponentsInGraph {
    public static List<Integer> componentsInGraph(List<List<Integer>> gb) {
        Set<Integer> s = new HashSet<>();
        List<Integer> res = new ArrayList<>();

        for (List<Integer> g : gb) {
            s.add(g.get(0));
            s.add(g.get(1));
        }

        int n = Collections.max(s);
        List<Integer>[] graph = new ArrayList[n+1];
        boolean[] visited = new boolean[n+1];
        List<Integer> count = new ArrayList<>();

        for (int i = 1; i <= n; i++) {
            graph[i] = new ArrayList<>();
        }

        for (List<Integer> g : gb) {
            int u = g.get(0);
            int v = g.get(1);
            graph[u].add(v);
            graph[v].add(u);
        }
        

        for (int i = 1; i <= n; i++) {
            if (!visited[i] && !graph[i].isEmpty()) { 
                int temp = dfs(graph, visited, i);
                count.add(temp);
            }
        }
    
        res.add(Collections.min(count));
        res.add(Collections.max(count));
        return res;

    }

    private static int dfs(List<Integer>[] graph, boolean[] visited, int i) {
        visited[i] = true;
        int count = 1;
    
        for (int v : graph[i]) {
            if (!visited[v]) {
                count += dfs(graph, visited, v);
            }
        }
    
        return count;
    }
}
