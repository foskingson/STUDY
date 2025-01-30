package neetCode250;

import java.util.HashMap;
import java.util.Map;

public class MajorityElement {
    public int majorityElement(int[] nums) {
        Map<Integer,Integer> map = new HashMap<>();
        int check = (nums.length+1)/2;

        for (int i : nums) {
            map.put(i, map.getOrDefault(i, 0)+1);
        }

        for (int k : map.keySet()) {
            if (map.get(k)>=check) {
                return k;
            }
        }

        return -1;
    }
}
