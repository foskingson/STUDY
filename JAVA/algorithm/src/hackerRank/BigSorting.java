package hackerRank;


import java.util.Collections;
import java.util.List;

public class BigSorting {
    public static List<String> bigSorting(List<String> unsorted) {
        Collections.sort(unsorted, (a, b) -> {
            if (a.length() != b.length()) {
                return Integer.compare(a.length(), b.length()); 
            }
            return a.compareTo(b); 
        });

        return unsorted; 
    }
}
