package hackerRank;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class IcecreamParlor {
    public static List<Integer> icecreamParlor(int m, List<Integer> arr) {
        List<Integer> res = new ArrayList<>();
        List<Ice> arr2 = new ArrayList<>();

        for (int i = 0; i < arr.size(); i++) {
            arr2.add(new Ice(i + 1, arr.get(i)));  
        }

        Collections.sort(arr2, (a, b) -> Integer.compare(a.val, b.val));

        int left = 0, right = arr2.size() - 1;
        while (left < right) {
            int sum = arr2.get(left).val + arr2.get(right).val;
            if (sum == m) {
                res.add(arr2.get(left).idx);  
                res.add(arr2.get(right).idx);  
                break;
            } else if (sum < m) {
                left++;
            } else {
                right--;
            }
        }

        Collections.sort(res);
        return res;
    }

    public static void main(String[] args) {
        List<List<Integer>> testCases = new ArrayList<>();
        testCases.add(new ArrayList<>(List.of(5, 75, 25)));
        testCases.add(new ArrayList<>(List.of(150, 24, 79, 50, 88, 345, 3)));
        testCases.add(new ArrayList<>(List.of(2, 1, 9, 4, 4, 56, 90, 3)));
        testCases.add(new ArrayList<>(List.of(230, 863, 916, 585, 981, 404, 316, 785, 88, 12, 70, 435, 384, 778, 887, 755, 740, 337, 86, 92, 325, 422, 815, 650, 920, 125, 277, 336, 221, 847, 168, 23, 677, 61, 400, 136, 874, 363, 394, 199, 863, 997, 794, 587, 124, 321, 212, 957, 764, 173, 314, 422, 927, 783, 930, 282, 306, 506, 44, 926, 691, 568, 68, 730, 933, 737, 531, 180, 414, 751, 28, 546, 60, 371, 493, 370, 527, 387, 43, 541, 13, 457, 328, 227, 652, 365, 430, 803, 59, 858, 538, 427, 583, 368, 375, 173, 809, 896, 370, 789)));
        
        for (List<Integer> arr : testCases) {
            List<Integer> result = icecreamParlor(100, arr);  
            System.out.println(result.get(0) + " " + result.get(1));
        }
    }

}

class Ice {
    int idx;  
    int val;  

    public Ice(int idx, int val) {
        this.idx = idx;
        this.val = val;
    }
}
