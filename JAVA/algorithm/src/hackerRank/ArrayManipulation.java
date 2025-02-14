package hackerRank;

import java.util.List;

public class ArrayManipulation {
    public static long arrayManipulation(int n, List<List<Integer>> queries) {
        long[] res = new long[n+1]; 

        for (List<Integer> query : queries) {
            int st = query.get(0);
            int end = query.get(1);
            int val = query.get(2);
            
            res[st] += val;  
            if (end + 1 <= n)  {
                res[end+1] -= val;
            }
        }

        long max = 0;
        long sum = 0;
        for (int i = 1; i <= n; i++) {
            sum += res[i];
            max = Math.max(max, sum);  
        }
        
        return max;
    }

    public static void main(String[] args) {
        int n = 5;
        List<List<Integer>> queries = List.of(
            List.of(1, 2, 100),
            List.of(2, 5, 100),
            List.of(3, 4, 100)
        );
        System.out.println(arrayManipulation(n, queries));  // 출력: 200
    }
}
