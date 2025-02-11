package hackerRank;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LegoBlocks {
    private static long pow(long num, int exp, long mod) {
        long res = num;
        while (exp-- > 1) {
            res = (res * num) % mod;
        }
        return res;
    }

    public static int legoBlocks(int h, int w) {
        long divisor = 1000000007;
        
        List<Long> singleRow = new ArrayList<>(Arrays.asList(0L, 1L, 2L, 4L, 8L));
        List<Long> total = new ArrayList<>(Arrays.asList(0L, 1L,
                pow(2, h, divisor),
                pow(4, h, divisor),
                pow(8, h, divisor)));
        
        for (int i = 5; i <= w; i++) {
            long val = (singleRow.get(i - 1) + singleRow.get(i - 2) +
                    singleRow.get(i - 3) + singleRow.get(i - 4)) % divisor;
            singleRow.add(val);
            total.add(pow(val, h, divisor));
        }
        
        List<Long> invalid = new ArrayList<>(Arrays.asList(0L, 0L));
        
        for (int cut = 2; cut <= w; cut++) {
            long anum = 0;
            for (int i = 1; i < cut; i++) {
                long l = total.get(i) - invalid.get(i);
                long r = total.get(cut - i);
                anum += ((l * r) % divisor);
            }
            invalid.add(anum % divisor);
        }
        
        long r = (total.get(w) - invalid.get(w)) % divisor;
        while (r < 0)
            r += divisor;
        
        return (int) r;
    }

}
