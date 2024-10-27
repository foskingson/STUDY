import java.util.Scanner;

/*
 * 그리디 알고리즘
 * 가장 주의할 점은 조건을 잘봐야 한다. 해당문제는 다음과 같은 조건으로 그리디가 성립한다.
 * Ai는 Ai-1의 배수
 */
public class Backjoon11047 {    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int k = sc.nextInt();

        int [] a = new int[n];
        for (int i = 0; i < a.length; i++) {
            a[i] = sc.nextInt();
        }

        solution(n,k,a);
    }

    private static void solution(int n, int k, int[] a) {
        int count = 0;
        for (int i = n-1; i >= 0; i--) {
            if (a[i]<=k) {
                count+=k/a[i];
                k=k%a[i];
            }
        }
        System.out.println(count);
    }
}
