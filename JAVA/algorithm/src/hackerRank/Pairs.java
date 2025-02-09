package hackerRank;

import java.util.Collections;
import java.util.List;

public class Pairs {
    public static int pairs(int k, List<Integer> arr) {
        Collections.sort(arr);
        int left=0, right=arr.size()-1;
        int count = 0;

        while (right<arr.size()) {
            int temp = arr.get(right)-arr.get(left);

            if (temp==k) {
                count++;
                left++;
                right++;
            }else if(temp>k){
                left++;
            }else{
                right--;
            }

            if (left == right) { // left와 right가 같아지면 right 증가
                right++;
            }
        }

        return count;
    }
}
