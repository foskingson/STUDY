import java.util.PriorityQueue;
import java.util.Scanner;

public class Backjoon11286 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] x = new int[n];
        
        for (int i = 0; i < x.length; i++) {
            x[i] =sc.nextInt();
        }
        solution(n,x);

        sc.close();
    }

    private static void solution(int n, int[] x) {
        
        PriorityQueue<Integer> q = new PriorityQueue<>((o1,o2)->{
            // 절댓값이 작은 데이터 우선
            // 절댓값이 같을 경우 음수 우선
            int abs1 = Math.abs(o1);
            int abs2 = Math.abs(o2);
            if (abs1==abs2) {
                return o1 > o2 ? 1:-1;
            }
            return abs1 - abs2;
        });

        for (int i = 0; i < n; i++) {
            if (x[i]==0) {
                if (q.isEmpty()) {
                    System.out.println(0);
                }else{
                    System.out.println(q.poll());
                }
            }else{
                q.add(x[i]);
            }
        }
    }
}
