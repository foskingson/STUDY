import java.util.ArrayList;
import java.util.Scanner;

public class Backjoon11724 {
    static boolean visited[];
    static ArrayList<Integer>[] a;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();   
        int m = sc.nextInt();  
        visited = new boolean[n+1];
        a = new ArrayList[n+1];

        for (int i = 1; i < n+1; i++) {
            a[i] = new ArrayList<>();
        }
        for (int i = 0; i < m; i++) {
            int s = sc.nextInt();
            int e =sc.nextInt();
            a[s].add(e);
            a[e].add(s);
        }

        int count = 0;
        for (int i = 1; i < n+1; i++) {
            if (!visited[i]) {
                count++;
                dfs(i);
            }
        }

        System.out.println(count);

        sc.close();
    }
    private static void dfs(int v) {
        if (visited[v]) {
            return;
        }
        visited[v]=true;
        for (int i : a[v]) {
            if (!visited[i]) {
                dfs(i);
            }
        }
    }
}

