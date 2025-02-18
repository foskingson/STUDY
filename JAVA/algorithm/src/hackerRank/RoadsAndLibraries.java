package hackerRank;

import java.util.ArrayList;
import java.util.List;

public class RoadsAndLibraries {
    static List<Integer>[] graph;
    static boolean[] visited;

    public static long roadsAndLibraries(int n, int c_lib, int c_road, List<List<Integer>> cities) {
        if (c_lib <= c_road) {
            return (long) n * c_lib;
        }

        graph = new ArrayList[n+1];
        visited=new boolean[n+1];
        for (int i = 1; i <= n; i++) {
            graph[i] = new ArrayList<>();
        }

        for (List<Integer> city : cities) {
            int a = city.get(0);
            int b = city.get(1);
            graph[a].add(b);
            graph[b].add(a);
        }

        long res=0;
        for (int i = 1; i <= n; i++) {
            if (!visited[i]) {
                int size = dfs(i);
                res += c_lib + (size - 1) * c_road;
            }
        }
        return res;
    }
    
    private static int dfs(int i) {
        visited[i]=true;
        int size=1;

        for (int v : graph[i]) {
            if (!visited[v]) {
                size+=dfs(v);
            }
        }
        return size;
    }
}
