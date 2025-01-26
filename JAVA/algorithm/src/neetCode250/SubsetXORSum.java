package neetCode250;


public class SubsetXORSum {

    public int subsetXORSum(int[] nums) {
        return backtrack(nums, 0, 0);
    }

    private int backtrack(int[] nums, int idx, int currentXor) {
        if (idx == nums.length) {
            return currentXor; 
        }

        int exclude = backtrack(nums, idx + 1, currentXor); 
        int include = backtrack(nums, idx + 1, currentXor ^ nums[idx]);
        return exclude + include;
    }

    public static void main(String[] args) {
        SubsetXORSum solution = new SubsetXORSum();
        int[] nums = {1, 3};
        System.out.println("XOR 합: " + solution.subsetXORSum(nums)); // 출력: 6
    
        int[] nums2 = {5, 1, 6};
        System.out.println("XOR 합: " + solution.subsetXORSum(nums2)); // 출력: 28
    
        int[] nums3 = {1, 2, 3, 4};
        System.out.println("XOR 합: " + solution.subsetXORSum(nums3)); // 출력: 48
    }
}