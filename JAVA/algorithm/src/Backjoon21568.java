import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Backjoon21568 {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        int a = Integer.parseInt(st.nextToken());
        int b = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());

        int gcd=gcd(a,b);
        if (c%gcd!=0) {
            System.out.println(-1);
        }else{
            int k = (int)c/gcd;
            int[] xy = solution(a, b);
            System.out.println(xy[0]*k+" "+xy[1]*k);
        }
    }

    private static int[] solution(int a, int b) {
        int[] xy= new int[2];
        if (b==0) {
            xy[0]=1; xy[1]=0;
            return xy;
        }
        int q = a/b;
        int[] temp =solution(b, a%b);
        xy[0] = temp[1];
		xy[1] = temp[0] - temp[1] * q;
		return xy;
    }

    private static int gcd(int a, int b) {
        while (b!=0) {
            int r = a%b;
            a=b;
            b=r;
        }
        return a;
    }


}
