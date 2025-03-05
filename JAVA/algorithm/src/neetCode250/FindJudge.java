package neetCode250;

import java.util.ArrayList;
import java.util.List;

public class FindJudge {
    public int findJudge(int n, int[][] trust) {
        List<Integer>[] g = new ArrayList[n+1];
        int[] check = new int[n+1];

        for (int i = 0; i < g.length; i++) {
            g[i]= new ArrayList<>();
        }
        
        for (int[] t : trust) {
            int a=t[0];
            int b=t[1];

            g[a].add(b);
        }

        for (int i = 1; i <= n ; i++) {
            if (g[i].size()>0) {
                check[i]-=n;
            }
            for (int v : g[i]) {
                check[v]++;
            }
        }

        for (int i = 1; i <=n; i++) {
            if (check[i]==n-1) {
                return i;
            }
        }

        return -1;
    }

    public static void main(String[] args) {
        FindJudge f = new FindJudge();
        f.findJudge(2, new int[][]{{1,2}});
    }
}
