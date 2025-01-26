package hackerRank;

import java.util.Collections;
import java.util.List;

public class FindMedian {
    public static int findMedian(List<Integer> arr) {
        Collections.sort(arr);
        int st = 0, end = arr.size()-1;
        int mid = (st+end)/2;
        return arr.get(mid);
    }
}
