package neetCode250;

import java.util.HashMap;
import java.util.Map;

public class IsAnagram {
    public boolean isAnagram(String s, String t) {
        if(s.length()!=t.length()){
            return false;
        }
        char[] ca1 = s.toCharArray();
        char[] ca2 = t.toCharArray();
        Map<Character,Integer> map1 = new HashMap<>();
        Map<Character,Integer> map2 = new HashMap<>();
        
        for (char c : ca1) {
            map1.put(c, map1.getOrDefault(c, 0)+1);
        }
        for (char c : ca2) {
            map2.put(c, map2.getOrDefault(c, 0)+1);
        }

        if (map1.equals(map2)) {
            return true;
        }

        return false;
    }
}
