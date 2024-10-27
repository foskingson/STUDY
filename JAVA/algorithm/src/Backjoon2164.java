import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Backjoon2164 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        Queue<Integer> queue = new LinkedList<>();
        int check = 1;

        for (int i = 1; i<=n; i++) {
            queue.add(i);
        }

        while(queue.size()>1){
            if (check%2==1) {
                queue.poll();
            }else{
                int temp = queue.poll();
                queue.add(temp);
            }
            check++;
        }
        System.out.println(queue.peek());
        sc.close();
    }
}
