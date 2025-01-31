package hackerRank;

import java.util.List;

public class BalancedSums {
    public static String balancedSums(List<Integer> arr) {
        int ts = 0;
        for (int num : arr) {
            ts += num;
        }

        int ls=0;

        
        for (int i = 0; i < arr.size(); i++) {
            int rs = ts - ls - arr.get(i);
            
            if (ls == rs) {
                return "YES";
            }
            
            ls += arr.get(i);
        }
        
        return "NO";
    }

    public static void main(String[] args) {
        System.out.println(balancedSums(List.of(2,0,0,0)));
    }
}
