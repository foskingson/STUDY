package hackerRank;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MaxSubarray {
    public static List<Integer> maxSubarray(List<Integer> arr) {
        int maxSubarraySum = Integer.MIN_VALUE;
        int currentSum = 0;
        int maxSubsequenceSum = 0;
        boolean hasPositive = false;
        int maxNegative = Integer.MIN_VALUE;
        for (int num : arr) {
            // Kadane's Algorithm for Maximum Subarray Sum
            currentSum += num;
            if (currentSum > maxSubarraySum) {
                maxSubarraySum = currentSum;
            }
            if (currentSum < 0) {
                currentSum = 0;
            }
            if (num > 0) {
                maxSubsequenceSum += num;
                hasPositive = true;
            }
            if (num < 0 && num > maxNegative) {
                maxNegative = num;
            }
        }
        if (!hasPositive) {
            maxSubsequenceSum = maxNegative;
        }
        
        return Arrays.asList(maxSubarraySum, maxSubsequenceSum);
    }
    public static void main(String[] args) {
        maxSubarray(List.of(2,-1,2,3,4,-5
        ));
    }
}
