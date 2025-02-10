package hackerRank;

import java.util.List;

public class EqualStacks {
    public static int equalStacks(List<Integer> h1, List<Integer> h2, List<Integer> h3) {
        int s1 = findSum(h1);
        int s2 = findSum(h2);
        int s3 = findSum(h3);

        while (!(s1==s2 && s2==s3)) {
            if (s1>=s2 && s1>=s3) {
                s1-=h1.get(0);
                h1.remove(0);
            }else if(s2>=s1 && s2>=s3){
                s2-=h2.get(0);
                h2.remove(0);
            }else if(s3>=s1 && s3>=s2){
                s3-=h3.get(0);
                h3.remove(0);
            }
        }

        return s1;
    }

    private static int findSum(List<Integer> h){
        int sum = 0;
        for (int i : h) {
            sum+=i;
        }
        return sum;
    }
}
