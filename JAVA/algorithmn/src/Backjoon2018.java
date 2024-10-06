import java.util.Scanner;

public class Backjoon2018 {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        int n = sc.nextInt();
        solution(n);
        sc.close();
    }

    private static void solution(int n) {
        int [] a = new int[n];  
        int sum=1;
        int st = 0;
        int end = 0;
        int count = 0;
        for (int i = 0; i < n; i++) {
            a[i]=i+1;
        }

        while(end<n){
            if (sum>n) {
                sum-=a[st];
                st++;
            }else if(sum<n){
                end++;
                sum+=a[end];
            }else{
                count++;
                end++;
                if(end>=n)break;
                sum+=a[end];
            }
        }
        System.out.println(count);
    }
}
