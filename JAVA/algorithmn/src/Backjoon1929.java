import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Backjoon1929 {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());

        
        int m = Integer.parseInt(st.nextToken());
        int n = Integer.parseInt(st.nextToken());

        boolean[] a = new boolean[n+1];
        for (int i = 2; i <=n; i++) {
            a[i] = true;    // 모든 수를 소수로 놓고 시작
        }

        for (int i = 2; i <= Math.sqrt(n); i++) {
            if (a[i]==false) {  // 이미 소수가 아닌 애들은 배수도 소수가 아니므로 건너뜀
                continue;
            }
            for (int j = i*i; j <=n; j=j+i) {   // 배수를 확인해가며 소수가 아닌 것들을 걸러냄
                a[j] = false;
            }
        }

        for (int i = m; i <= n; i++) {
            if (a[i]) {
                System.out.println(i);
            }
        }
    }


}
