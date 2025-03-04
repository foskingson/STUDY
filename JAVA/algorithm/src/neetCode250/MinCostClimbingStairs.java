package neetCode250;

public class MinCostClimbingStairs {
    public int minCostClimbingStairs(int[] cost) {
        int n = cost.length;
        int[] dp = new int[n+1];
        
        dp[1]=cost[0];

        for (int i = 2; i <= n; i++) {
            dp[i] = Math.min(dp[i - 1] + cost[i - 1], dp[i - 2] + cost[i - 2]);
        }
        return Math.min(dp[n], dp[n - 1]);
    }

    public static void main(String[] args) {
        MinCostClimbingStairs m = new MinCostClimbingStairs();
        m.minCostClimbingStairs(new int[]{10,15,20});
    }
}
