package hackerRank;

import java.util.List;
import java.util.PriorityQueue;

public class Cookies {
    public static int cookies(int k, List<Integer> A) {
        PriorityQueue<Integer> pq = new PriorityQueue<>(A);
        int count=0;
        while (pq.peek()<k) {
            if (pq.size()>=2) {
                count++;
                int a = pq.poll();
                int b = pq.poll();
                pq.add(1*a+2*b);
            }else{
                return -1;
            }
        }
        return count;
    }

    public static void main(String[] args) {
        cookies(9, List.of(1,1,1));
    }
}
