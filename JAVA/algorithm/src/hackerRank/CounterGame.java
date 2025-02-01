
package hackerRank;

public class CounterGame {
    public static String counterGame(long n) {
        int win = 0; // 0: 루이스   1: 리차드
        while (n!=1) {
            if ((n & (n - 1)) == 0) {
                n/=2;
            }else{
                n -= Long.highestOneBit(n);
            }

            win = 1 - win;
        }

        if (win==0) {
            return "Richard";
        }else{
            return "Louise";
        }
    
    }

    public static void main(String[] args) {
        System.out.println(counterGame(1533726144));
    }
}
