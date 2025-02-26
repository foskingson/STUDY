package neetCode250;

import java.util.ArrayList;
import java.util.List;

public class Subsets {
    List<List<Integer>> res = new ArrayList<>();
    public List<List<Integer>> subsets(int[] nums) {
        back(nums,new ArrayList<>(), 0);
        return res;
    }

    private void back(int[] nums, ArrayList<Integer> temp, int idx) {
        res.add(new ArrayList<>(temp));

        for (int i = idx; i < nums.length; i++) {
            temp.add(nums[i]);
            back(nums, temp, i+1);
            temp.remove(temp.size() - 1);
        }
    }
}
