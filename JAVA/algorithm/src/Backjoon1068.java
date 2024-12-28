import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Backjoon1068 {
    static int n;
    static ArrayList<Integer>[] t;
    static int check;
    static boolean[] visited;
    static int count = 0;
    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(bf.readLine());
        StringTokenizer st = new StringTokenizer(bf.readLine());
        t=new ArrayList[n];
        for (int i = 0; i < n; i++) {
            t[i] = new ArrayList<>();
        }
        int root=0;
        for (int i = 0; i < n; i++) {
            int temp = Integer.parseInt(st.nextToken());
            if (temp==-1) {
                root=i;
                continue;
            }
            t[i].add(temp);
            t[temp].add(i);
        }
        check = Integer.parseInt(bf.readLine());
        visited = new boolean[n];

        dfs(root);
        System.out.println(count);
    }
    private static void dfs(int v) {
        if (v==check) {
            return;
        }
        visited[v]=true;
        boolean isLeaf=true;
        for (int u : t[v]) {
            if (!visited[u] && u != check) {
                isLeaf=false;
                visited[u]=true;
                dfs(u);
            }
        }
        if (isLeaf) {
            count++;
        }
    }
}
