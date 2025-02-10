package hackerRank;

import java.util.ArrayList;
import java.util.List;

public class MaxSubarray {
    public static List<Integer> maxSubarray(List<Integer> arr) {
        List<Integer> res = new ArrayList<>();
        if (arr.size()==1) {
            res.add(arr.get(0));
        }

        int rest= findTotal(arr);
        int st=0;
        rest-=arr.get(st);

        while (arr.get(st)<0) {
            st++;
            rest-=arr.get(st);
            
        }
        int end=st;

        while (rest>0 || arr.get(end+1)>0) {
            end++;
            rest-=arr.get(end);
            if (end==arr.size()-1) {
                break;
            }
        }

        if (st<arr.size() && end<arr.size()) {
            int sum=0;
            for (int i = st; i <= end; i++) {
                sum+=arr.get(i);
            }
            res.add(sum);
        }else{
            res.add(findMax(arr));
        }

        

        int sum=0;
        for (int a : arr) {
            if (a<0) {
                continue;
            }
            sum+=a;
        }
        res.add(sum);

        return res;
    }

    private static int findTotal(List<Integer> arr) {
        int sum = 0;
        for (int i : arr) {
            sum+=i;
        }
        return sum;
    }

    private static int findMax(List<Integer> arr){
        int m = arr.get(0);
        for (int i : arr) {
            if (m<i) {
                m=i;
            }
        }
        return m;
    }

    public static void main(String[] args) {
        maxSubarray(List.of(2,-1,2,3,4,-5
        ));
    }
}
