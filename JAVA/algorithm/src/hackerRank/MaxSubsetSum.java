package hackerRank;

import java.util.ArrayList;
import java.util.List;

public class MaxSubsetSum {
    public static List<Long> maxSubsetSum(List<Integer> k) {
        List<Long> res = new ArrayList<>();

        for (int i : k) {
            res.add(findFactorSum(i));
        }

        return res;
    }

    private static long findFactorSum(int i) {
        long sum = 0;
        for (int j = 1; j <= Math.sqrt(i); j++) {
            if (i%j==0) {
                sum+=j;
                if (j!=i/j) {
                    sum+=i/j;
                }
            }
        }
        return sum;
    }
}
