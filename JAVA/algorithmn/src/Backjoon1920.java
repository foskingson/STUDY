import java.util.Arrays;
import java.util.Scanner;

public class Backjoon1920 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] a1 = new int[n];
        for (int i = 0; i < n; i++) {
            a1[i]=sc.nextInt();
        }

        int m = sc.nextInt();
        int[] a2 = new int[m];
        for (int i = 0; i < m; i++) {
            a2[i]=sc.nextInt();
        }

        solution(a1,a2);
        
        sc.close();
    }

    private static void solution(int[] a1, int[] a2) {
        Arrays.sort(a1);
        for (int i = 0; i < a2.length; i++) {
            int check = 0;
            int st =0; int end=a1.length-1;
            int target = a2[i];
            while(st<=end){
                int mid = (st+end)/2;
                if(a1[mid]<target){
                    st = mid+1;
                }else if(a1[mid]>target){
                    end = mid -1;
                }else{
                    check=1;
                    break;
                }
            }
            System.out.println(check);
        }
    }
}
