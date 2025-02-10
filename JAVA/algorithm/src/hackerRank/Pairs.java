package hackerRank;

import java.util.Collections;
import java.util.List;

public class Pairs {
    public static int pairs(int k, List<Integer> arr) {
        Collections.sort(arr); 
        int left = 0, right = 1, count = 0;
        int n = arr.size();
        
        while (right < n) {
            int diff = arr.get(right) - arr.get(left);
    
            if (diff == k) { 
                count++; 
                left++; 
                right++;
            } else if (diff < k) { 
                right++; 
            } else { 
                left++; 
            }
    
            if (left == right) {
                right++;
            }
        }
        
        return count;
    }
}
