package hackerRank;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MaxMin {
    public static int maxMin(int k, List<Integer> arr) {
        Collections.sort(arr);
        int res = Integer.MAX_VALUE;

        for (int i = k-1; i < arr.size(); i++) {
            res = Math.min(res, arr.get(i)-arr.get(i-k+1));
        }
        
        return res;
    }

    public static void main(String[] args) {
        System.out.println(maxMin(3, new ArrayList<>(List.of(10,100,300,200,1000,20,30))));
    }
}
