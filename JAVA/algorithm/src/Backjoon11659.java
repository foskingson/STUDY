import java.util.Scanner;

public class Backjoon11659 {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        int[] a =new int[n+1];
        a[0] = 0;
        for (int i = 1; i < n+1; i++) {
            a[i] = sc.nextInt()+a[i-1];
        }

        solution(n,m,a,sc);
        sc.close();
    }

    private static void solution(int n, int m, int[] a,Scanner sc) {
        for (int i = 0; i < m; i++) {
            System.out.println(-(a[sc.nextInt()-1])+a[sc.nextInt()]);
        }
    }
}
