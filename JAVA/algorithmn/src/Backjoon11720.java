import java.util.Scanner;

public class Backjoon11720 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        String nums = sc.next();
        solution(n,nums);

        sc.close();
    }

    private static void solution(int n, String nums) {
        int sum = 0;
        for (int i = 0; i < n; i++) {
            sum+=Integer.parseInt(nums.substring(i, i+1));
        }
        System.out.println(sum);
    }
}
