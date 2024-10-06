import java.util.Scanner;

public class Backjoon11689 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        long n = sc.nextLong();  // n은 long형으로 받아야 합니다.

        long result = n;  // 결과를 n으로 초기화

        // 소수 찾기
        for (long i = 2; i <= Math.sqrt(n)  ; i++) {
            if (n % i == 0) {  // i가 n의 약수인 경우
                while (n % i == 0) {  // n을 i로 나누어 제거
                    n /= i;
                }
                result -= result / i;  // 오일러 피 함수 공식
            }
        }

        // 남아있는 소수가 있는 경우
        if (n > 1) {
            result -= result / n;  // n이 소수인 경우 처리
        }

        System.out.println(result);
    }
}
