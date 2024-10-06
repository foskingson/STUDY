import java.io.*;
import java.util.*;

public class Backjoon11003_2 {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new FileReader("txt/SlidingWindow_5000000.txt"));
        StringBuilder sb = new StringBuilder();
        String line;

        // 입력 파일을 한 번에 읽어서 처리
        while ((line = br.readLine()) != null) {
            sb.append(line).append(" ");
        }
        br.close();

        // 숫자 배열로 변환
        String[] tokens = sb.toString().split(" ");
        int n = tokens.length;
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = Integer.parseInt(tokens[i]);
        }

        // 윈도우 크기 설정
        int l;
        if (n == 100) {
            l = 3;
        } else {
            l = 100;
        }

        long beforeTime = System.currentTimeMillis(); // 코드 실행 전에 시간 받아오기
        solution(n, l, a);
        long afterTime = System.currentTimeMillis(); // 코드 실행 후에 시간 받아오기
        long diffTime = afterTime - beforeTime; // 두 개의 실행 시간

        System.out.println("실행 시간(ms): " + diffTime); // 실행 시간 출력
    }

    private static void solution(int n, int l, int[] a) throws IOException {
        Deque<Node> d = new LinkedList<>();
        StringBuilder sb = new StringBuilder(); // 결과를 모아서 한 번에 출력

        for (int i = 0; i < n; i++) {
            // 현재 값보다 큰 값들을 제거
            while (!d.isEmpty() && d.getLast().val > a[i]) {
                d.removeLast();
            }
            d.add(new Node(i, a[i]));

            // 윈도우 크기를 초과한 요소를 제거
            if (!d.isEmpty() && d.getFirst().idx <= i - l) {
                d.removeFirst();
            }

            // 최소값을 StringBuilder에 저장
            sb.append(d.getFirst().val).append(" ");
        }

        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out)); // BufferedWriter 사용
        bw.write(sb.toString().trim()); // 한 번에 출력
        bw.flush(); // 출력 플러시
    }
}

