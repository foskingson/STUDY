import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Backjoon2252 { // 위상정렬
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        int n = Integer.parseInt(st.nextToken()); 
        int m = Integer.parseInt(st.nextToken()); 
        ArrayList<Integer>[] student = new ArrayList[n+1];

        for (int i = 1; i <= n; i++) {
            student[i]=new ArrayList<>();
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(bf.readLine());
            int a = Integer.parseInt(st.nextToken()); 
            int b = Integer.parseInt(st.nextToken()); 
            student[a].add(b);
        }

        solution(student,n);
    }

    private static void solution(ArrayList<Integer>[] student,int n) {
        int[] indeg = new int[n+1];  // 각 노드별 진입차수
        for (int i = 1; i <=n; i++) {
            for (int v : student[i]) {
                indeg[v]+=1;
            }
        }

        Queue<Integer> vlist = new LinkedList<>();  // 진입차수가 0인 노드
        for (int i = 1; i <=n; i++) {
            if (indeg[i]==0) {
                vlist.add(i);
            }
        }

        while (!vlist.isEmpty()) {
            int v = vlist.poll();
            System.out.print(v+" ");
            for (int u : student[v]) {
                indeg[u]-=1;
                if (indeg[u]==0) {
                    vlist.add(u);
                }
            }
        }
    }
}
