package hackerRank;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BigSorting {
    public static List<String> bigSorting(List<String> unsorted) {
        List<Long> temp = new ArrayList<>();
        for (int i = 0; i < unsorted.size(); i++) {
            temp.add(Long.parseLong(unsorted.get(i)));
        }
        Collections.sort(temp);
        
        List<String> sorted = new ArrayList<>();
        for (int i = 0; i < temp.size(); i++) {
            sorted.add(Long.toString(temp.get(i)));
        }
        return sorted;
    }
}
