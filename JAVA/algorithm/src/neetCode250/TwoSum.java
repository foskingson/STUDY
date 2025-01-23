package neetCode250;

import java.util.HashMap;

public class TwoSum {
    public int[] twoSum(int[] nums, int target) {
        HashMap<Integer,Integer> map = new HashMap<>(); // <val, index>

        for (int i = 0; i < nums.length; i++) {
            int num = nums[i];
            int diff = target - num;

            if (map.containsKey(diff)) {
                return new int[]{i,map.get(diff)};
            }

            map.put(nums[i], i);
        }
        return new int[]{};
    }
}
