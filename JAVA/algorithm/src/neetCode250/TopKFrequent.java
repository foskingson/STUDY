package neetCode250;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class TopKFrequent {
    public int[] topKFrequent(int[] nums, int k) {
        int[] res = new int[k];
        Map<Integer, Integer> m = new HashMap<>();
        for (int num : nums) {
            m.put(num, m.getOrDefault(num, 0) + 1);
        }

        PriorityQueue<Map.Entry<Integer, Integer>> pq = new PriorityQueue<>(
            (a, b) -> b.getValue() - a.getValue() 
        );

        pq.addAll(m.entrySet());

        for (int i = 0; i < k; i++) {
            res[i]=pq.poll().getKey();
        }

        return res;
    }

    public static void main(String[] args) {
        TopKFrequent t = new TopKFrequent();
        t.topKFrequent(new int[]{1,2,2,3,3,3}, 2);
    }
}
