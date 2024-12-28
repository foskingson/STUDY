import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Backjoon1806 {
    static int n;
    static int s;
    static int[] a;
    static int minLen=Integer.MAX_VALUE;
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        n = Integer.parseInt(st.nextToken());
        s = Integer.parseInt(st.nextToken());
        a = new int[n+1];
        st = new StringTokenizer(bf.readLine());
        for (int i = 1; i <= n; i++) {
            a[i] = Integer.parseInt(st.nextToken())+a[i-1];
        }

        solution();
        if (minLen==Integer.MAX_VALUE) {
            System.out.println(0);
        }else{
            System.out.println(minLen);
        }
    }
    private static void solution() {
        int st = 1;
        int end = 1;
        while (end<n+1) {
            if (a[end]-a[st-1]>=s) {
                if (minLen>end-(st-1)) {
                    minLen = end-(st-1);
                }
                st++;
            }else{
                end++;
            }
        }
    }
}
