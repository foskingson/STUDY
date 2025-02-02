package hackerRank;

import java.util.HashMap;
import java.util.Map;

public class Anagram {
    public static int anagram(String s) {
        int count =0 ;
        int n = s.length();
        if (n%2==1) {
            return -1;
        }

        int mid = n/2;
        String s1 = s.substring(0, mid);
        String s2 = s.substring(mid);

        Map<Character, Integer> freqMap1 = new HashMap<>();
        Map<Character, Integer> freqMap2 = new HashMap<>();

        for (char c : s1.toCharArray()) {
            freqMap1.put(c, freqMap1.getOrDefault(c, 0) + 1);
        }

        for (char c : s2.toCharArray()) {
            freqMap2.put(c, freqMap2.getOrDefault(c, 0) + 1);
        }

        for (char c : freqMap1.keySet()) {
            int count1 = freqMap1.get(c);
            int count2 = freqMap2.getOrDefault(c, 0);
            if (count1 > count2) {
                count += count1 - count2;  
            }
        }
        return count;
    }

    public static void main(String[] args) {
        System.out.println(anagram("asdfjoieufoa"));
    }
}
