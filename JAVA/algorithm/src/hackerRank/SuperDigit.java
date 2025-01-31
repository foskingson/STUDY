package hackerRank;

public class SuperDigit {
    public static int superDigit(String n, int k) {
        long sum = 0;
        for (char c : n.toCharArray()) {
            sum += c - '0';
        }
        
        sum *= k;
        
        while (sum >= 10) {
            long temp = 0;
            while (sum > 0) {
                temp += sum % 10;
                sum /= 10;
            }
            sum = temp;
        }
        
        return (int) sum;
    }
}
