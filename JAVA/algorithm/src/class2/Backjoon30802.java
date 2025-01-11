package class2;

import java.util.Scanner;

public class Backjoon30802 {    // 웰컴 키트
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();   
        int[] size = new int[6];
        for (int i = 0; i < size.length; i++) {
            size[i] = sc.nextInt();
        }
        int T = sc.nextInt(); int P = sc.nextInt();

        int count = 0;

        for (int i = 0; i < 6; i++) {
            if (size[i]%T==0) {
                count += size[i]/T;
            }else{
                count += size[i]/T+1;
            }
            
        }
        System.out.println(count);

        System.out.println(n/P + " " + n%P);
    }
}
