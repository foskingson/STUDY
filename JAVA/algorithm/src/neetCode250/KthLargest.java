package neetCode250;

import java.util.PriorityQueue;

public class KthLargest {
    PriorityQueue<Integer> pq = new PriorityQueue<>((a,b)-> b-a);
    int k;

    public KthLargest(int k, int[] nums) {
       this.k = k;
       for (int i : nums) {
            this.pq.add(i);
       }
    }
    
    public int add(int val) {
        pq.add(val);
        PriorityQueue<Integer> temp = new PriorityQueue<>(pq.comparator()); 
        temp.addAll(pq);
        int i=0;
        while (i!=k-1) {
            i++;
            temp.poll();
        }
        return temp.poll();
    }
}
