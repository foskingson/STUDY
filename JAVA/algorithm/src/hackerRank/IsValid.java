package hackerRank;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class IsValid {
    public static String isValid(String s) {
        Map<Character,Integer> map = new HashMap<>();

        for(char c : s.toCharArray()){
            map.put(c,map.getOrDefault(c,0)+1);
        }

        TreeMap<Integer,Integer> count = new TreeMap<>();

        for(int i : map.values()){
            count.put(i,count.getOrDefault(i,0)+1);
        }
        System.out.println(map +" , " + count);
        if(count.size() == 1){
            return "YES";
        }else if(count.size() == 2){
            int key1 = count.firstKey();
            int key2 = count.lastKey();
            int value1 = count.get(key1);
            int value2 = count.get(key2);

            if((key1 == 1 && value1 == 1) ||
            (key2 - key1 == 1 && value2 == 1)){
                return "YES";
            }
        }
        return "NO";
    }

    public static void main(String[] args) {
        System.out.println(isValid("aabbc"));   // 빈도 {1:1, 2:2}
    }
}
