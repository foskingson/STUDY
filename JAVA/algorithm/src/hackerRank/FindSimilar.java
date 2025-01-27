package hackerRank;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;


public class FindSimilar {

    public static long findSimilar(String a, String b) {
        long res= 0;
        long minus = 0;
        Map<Character, Integer> mapA = createMap(a);
        Map<Character, Integer> mapB = createMap(b);

        if (mapA.equals(mapB)) {
            res= calculatePermutations(a,mapA);
            if (a.contains("0")) {
                char[] c = a.toCharArray();
                Arrays.sort(c);
                String sortA = new String(c);
                sortA = sortA.substring(1);
                minus = calculatePermutations(sortA, createMap(sortA));
            }
        }else{
            res = calculatePermutations(b,mapB);  
            if (b.contains("0")) {
                char[] c = b.toCharArray();
                Arrays.sort(c);
                String sortB = new String(c);
                sortB = sortB.substring(1);
                minus = calculatePermutations(sortB, createMap(sortB));
            }
        }

        return res-minus;
    }

    private static long calculatePermutations(String s,Map<Character, Integer> map) {
        
        long num = factorial(s.length()); 
        
    
        for (int count : map.values()) {
            num /= factorial(count);  
        }
        
        return num;
    }


    private static long factorial(int n) {
        long result = 1;
        for (int i = 2; i <= n; i++) {
            result *= i;
        }
        return result;
    }


    private static Map<Character, Integer> createMap(String s) {
        Map<Character, Integer> map = new HashMap<>();
        for (char c : s.toCharArray()) {
            map.put(c, map.getOrDefault(c, 0) + 1);
        }
        return map;
    }

    public static void main(String[] args) {
        System.out.println(findSimilar("7343101008", "7303008114"));
    }
}