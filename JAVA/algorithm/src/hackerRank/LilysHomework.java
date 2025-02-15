package hackerRank;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;


public class LilysHomework {
    public static int lilysHomework(List<Integer> arr) {
        List<Integer> sortedArr = new ArrayList<>(arr);
        Map<Integer,Integer> sortedMap = new TreeMap<>();
        for (int i = 0; i < arr.size(); i++) {
            sortedMap.put(arr.get(i), i);
        }

        int left=0;
        int res1=0;
        for (int key : sortedMap.keySet()) {
            int idx = sortedMap.get(key);
            if (idx!=left) {
                int temp = sortedArr.get(idx);
                sortedArr.set(idx, sortedArr.get(left));
                sortedMap.put(sortedArr.get(left), idx);
                sortedArr.set(left, temp);
                res1++;
            }
            left++;
        }

        List<Integer> sortedDescArr = new ArrayList<>(arr);
        Map<Integer, Integer> sortedDescMap = new TreeMap<>(Collections.reverseOrder());
        for (int i = 0; i < arr.size(); i++) {
            sortedDescMap.put(arr.get(i), i);
        }

        left=0;
        int res2=0;
        for (int key : sortedDescMap.keySet()) {
            int idx = sortedDescMap.get(key);
            if (idx!=left) {
                int temp = sortedDescArr.get(idx);
                sortedDescArr.set(idx, sortedDescArr.get(left));
                sortedDescMap.put(sortedDescArr.get(left), idx);
                sortedDescArr.set(left, temp);
                res2++;
            }
            left++;
        }

        return Math.min(res1, res2);
    }

}
