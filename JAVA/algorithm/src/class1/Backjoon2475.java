package class1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


public class Backjoon2475 { // 검증수
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        int sum = 0;
        while (st.hasMoreTokens()) {    
            sum+=Math.pow(Integer.parseInt(st.nextToken()), 2);
        }
        System.out.println(sum%10);
    }
}
