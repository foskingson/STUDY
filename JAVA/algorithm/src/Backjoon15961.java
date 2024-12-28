import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Backjoon15961 {
    static int n, d, k, c;
    static int[] arr, visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        d = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        arr = new int[n];

        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }

        // 번호마다 먹은 스시 개수를 저장할 배열 (먹었던 것을 또 먹은 경우에는 +1을 해주면 안되므로)
        visited = new int[d + 1];

        System.out.println(slide());
    }

    static int slide() {
        // inSlide는 k 크기의 슬라이드 내에서 먹은 중복없는 스시 개수, chance는 찬스까지 고려해 먹을 수 있는 개수
        int inSlide = 0, chance;
        // 일단 처음 k개의 슬라이드에 담기
        for (int i = 0; i < k; i++) {
            if (visited[arr[i]] == 0) {
                inSlide++;
            }
            visited[arr[i]]++;
        }
        chance = inSlide;
        for (int i = 1; i < n; i++) {
            // 슬라이드에 찬스 번호가 들어있지 않으면 1개 더 먹을 수 있다
            if (chance <= inSlide) {
                if (visited[c] == 0) {
                    chance = inSlide + 1;
                } else {
                    chance = inSlide;
                }
            }
            // 슬라이드 이동 시, 앞쪽 스시는 못먹게 되고, 한번도 먹은적이 없다면 슬라이드 내에서 먹은 스시 개수 -1
            visited[arr[i - 1]]--;
            if (visited[arr[i - 1]] == 0) {
                inSlide--;
            }
            // 슬라이드 이동 시, 뒤쪽 스시 먹게 되고, 한번도 먹은적 없다면 슬라이드 내에서 먹은 스시 개수 +1
            // 회전초밥은 회전하므로 % n 을 사용해야한다
            if (visited[arr[(i + k - 1) % n]] == 0) {
                inSlide++;
            }
            visited[arr[(i + k - 1) % n]]++;
        }
        return chance;
    }
}
