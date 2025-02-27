package neetCode250;

import java.util.PriorityQueue;

public class LastStoneWeight {
    public int lastStoneWeight(int[] stones) {
        PriorityQueue<Integer> pq = new PriorityQueue<>((a,b)->{
            return b-a;
        });
        for (int stone : stones) {
            pq.add(stone);
        }

        while (pq.size()>1) {
            int a = pq.poll();
            int b = pq.poll();
            if (a-b==0) {
                continue;
            }else{
                pq.add(a-b);
            }
        }

        if (!pq.isEmpty()) {
            return pq.peek();
        }else{
            return 0;
        }
    }
}
