import java.util.Scanner;

public class Backjoon2750 { // 버블정렬
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int[] a = new int[n];
        for (int i = 0; i < a.length; i++) {
            a[i] = sc.nextInt();
        }

        solution(n,a);
        sc.close();
    }

    private static void solution(int n, int[] a) {
        for (int i = a.length-1; i >=0; i--) {
            for (int k = 0; k < i; k++) {
                if (a[k]>a[k+1]) {
                    int temp = a[k];
                    a[k]=a[k+1];
                    a[k+1]=temp;
                }
            }
        }

        for (int i : a) {
            System.out.println(i);
        }
    }
}
