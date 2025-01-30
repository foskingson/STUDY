package hackerRank;

import java.util.ArrayList;
import java.util.List;

public class DynamicArray {
    public static List<Integer> dynamicArray(int n, List<List<Integer>> queries) {
        List<List<Integer>> a = new ArrayList<>();
        List<Integer> res = new ArrayList<>();
        int lastAnswer = 0;

        for (int i = 0; i < n; i++) {
            a.add(new ArrayList<>()); 
        }

        for (List<Integer> q : queries) {
            if (q.get(0)==1) {
                int idx = (q.get(1)^lastAnswer)%n;
                a.get(idx).add(q.get(2));
            }else{
                int idx = (q.get(1)^lastAnswer)%n;
                lastAnswer = a.get(idx).get(q.get(2)%a.get(idx).size());
                res.add(lastAnswer);
            }
        }
        return res;
    }
}
