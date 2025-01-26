package hackerRank;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SockMerchant {
    public static int sockMerchant(int n, List<Integer> ar) {
        Map<Integer,Integer> map = new HashMap<>();
        int count = 0;
        for (int i : ar) {
            if (map.containsKey(i)) {
                map.remove(i);
                count++;
            }else{
                map.put(i, 0);
            }
        }
        return count;
    }

    public static void main(String[] args) {
        SockMerchant s = new SockMerchant();
        System.out.println(s.sockMerchant(9, List.of(10,20,20,10,10,30,50,10,20)));
    }
}
