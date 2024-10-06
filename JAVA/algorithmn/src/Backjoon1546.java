import java.util.Scanner;

public class Backjoon1546 { // 평균 구하기
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        int n = sc.nextInt();
        int[] score = new int[n];
        for (int i = 0; i < score.length; i++) {
            score[i] = sc.nextInt();
        }
        solution(n,score);

        sc.close();
    }

    private static void solution(int n, int[] score) {
        double sum = 0;
        int max = score[0];
        for (int i : score) {
            if (max < i) {
                max = i;
            }
        }

        for (double i : score) {
            sum+= i/max*100;
        }
        System.out.println(sum/n);
    }
}
