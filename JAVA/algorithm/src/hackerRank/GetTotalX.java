package hackerRank;

import java.util.List;

public class GetTotalX {
    public static int getTotalX(List<Integer> a, List<Integer> b) {
        int count = 0;

        int lcm_a = a.get(0);
        for (int i = 1; i < a.size(); i++) {
            lcm_a = lcm(lcm_a, a.get(i));
        }

        int gcd_b = b.get(0);
        for (int i = 1; i < b.size(); i++) {
            gcd_b = gcd(gcd_b, b.get(i));
        }

        int x = lcm_a;
        while (x <= gcd_b) {
            if (gcd_b % x == 0) {
                count++;
            }
            x += lcm_a; 
        }

        return count;
    }

    private static int gcd(int a,int b){
        while (b!=0) {
            int temp = a%b;
            a=b;
            b=temp;
        }
        return a;
    }

    
    private static int lcm(int a,int b){
        return a*b/gcd(a,b);
    }
}
