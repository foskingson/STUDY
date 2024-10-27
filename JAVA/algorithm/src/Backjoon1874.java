import java.util.Scanner;
import java.util.Stack;

public class Backjoon1874 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int a[] = new int[n];

        for (int i = 0; i < n; i++) {
            a[i] = sc.nextInt();
        }
        Stack<Integer> stack = new Stack<>();
        int num =1;
        StringBuffer bf = new StringBuffer();

        for (int i = 0; i < a.length; i++) {
            int su = a[i];
            if(su>=num){
                while(num<=su){
                    stack.push(num++);
                    bf.append("+\n");
                }
                stack.pop();
                bf.append("-\n");
            } 
            else{
                int temp = stack.pop();
                if (temp < su ) {
                    System.out.println("NO");
                    return;
                } else{
                    bf.append("-\n");
                }
            }
        }
        System.out.println(bf.toString());
        sc.close();
    }
}
