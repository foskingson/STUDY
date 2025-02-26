package neetCode250;

import java.util.ArrayList;
import java.util.List;

public class CombinationSum {
    List<List<Integer>> res = new ArrayList<>();
    int t;
    
    public List<List<Integer>> combinationSum(int[] nums, int target) {
        t = target;
        back(nums, new ArrayList<>(), 0, 0);
        return res;
    }

    private void back(int[] nums, ArrayList<Integer> a, int sum, int idx) {
        if (sum == t) {
            res.add(new ArrayList<>(a));
            return;
        }

        for (int i = idx; i < nums.length; i++) {
            if (sum + nums[i] > t) continue;
            a.add(nums[i]);
            back(nums, a, sum + nums[i], i);
            a.remove(a.size() - 1);
        }
    }
}
