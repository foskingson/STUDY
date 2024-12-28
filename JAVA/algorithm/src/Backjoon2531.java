import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Backjoon2531 {
    private static int N, D, K, C;
    private static int[] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());

        // 입력 처리
        N = Integer.parseInt(st.nextToken());
        D = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        
        arr = new int[N];
        for (int n = 0; n < N; n++) {
            arr[n] = Integer.parseInt(br.readLine());
        }

        // 해결 방법 실행 및 결과 출력
        sb.append(sol());
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

    private static int sol() {
        int ans = 0, count = 0;
        int[] check = new int[D+1]; // 초밥 종류 카운팅 배열

        // 초기 K개 초밥 카운팅
        for (int k = 0; k < K; k++) {
            if (check[arr[k]] == 0) count++;
            check[arr[k]]++;
        }

        // 슬라이딩 윈도우
        for (int n = 0; n < N; n++) {
            // 쿠폰 초밥 추가 체크
            if (check[C] == 0) ans = Math.max(ans, count+1);
            else ans = Math.max(ans, count);

            // 첫 번째 초밥 제거 및 마지막 초밥 추가
            check[arr[n]]--;
            if(check[arr[n]] == 0) count--;
            if(check[arr[(n+K)%N]] == 0) count++;
            check[arr[(n+K)%N]]++;
        }

        return ans; // 최대 종류 수 반환
    }
}
