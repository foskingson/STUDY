package hackerRank;

public class SumXor {
    public static long sumXor(long n) {
        if (n == 0) {
            return 1;
        }

        long count = 0;
        while (n > 0) {
            long check = n&1;
            if (check == 0) {
                count++; 
            }
            n >>= 1; 
        }

        return (1L << count); 
    }

    public static void main(String[] args) {
        System.out.println(sumXor(4)); // 4의 경우 출력값은 3
    }
}
