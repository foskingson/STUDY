import java.util.ArrayList;
import java.util.Scanner;

public class ReverseString {
    public static void main(String[] args){
        Scanner in=new Scanner(System.in);
        int num = in.nextInt();
        solution(num,in);
        in.close();
    }

    private static void solution(int num,Scanner in) {
        String[] arr = new String[num];

        // 배열 초기화
        for (int i = 0; i < num; i++) {
            arr[i] = ""; // 각 요소를 빈 문자열로 초기화
        }
        
        String temp;
        for (int i = 0; i < num; i++) {
            temp= in.next();
            for (int k = temp.length()-1; k>=0; k--) {
                arr[i]+=temp.charAt(k);
            }
        }

        for (int i = 0; i < num; i++) {
            System.out.println(arr[i]);
        }
    }
}
