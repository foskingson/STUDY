import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Deque;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Backjoon11003 {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());

        int N = Integer.parseInt(st.nextToken());
        int L = Integer.parseInt(st.nextToken());
        int[] a = new int[N];

        st = new StringTokenizer(bf.readLine());

        for (int i = 0; i < a.length; i++) {
            a[i] = Integer.parseInt(st.nextToken());
        }

        solution(N,L,a);
    }

    private static void solution(int n, int l, int[] a) {
        Deque<Node> deq = new LinkedList<>();
        StringBuilder sb = new StringBuilder();
        
        for (int i = 0; i < n; i++) {
            if (!deq.isEmpty() && deq.getFirst().idx == i - l) {
                deq.removeFirst();
            }

            while (!deq.isEmpty() && deq.getLast().val > a[i]) {
                deq.removeLast();
            }

            deq.addLast(new Node(i, a[i]));

            sb.append(deq.getFirst().val).append(" ");
        }

        System.out.println(sb.toString().trim());
    }

}

class Node{
    int idx;
    int val;

    public Node(int idx, int val) {
        this.idx = idx;
        this.val = val;
    }
    
}