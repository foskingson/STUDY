import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.io.IOException;

/*
 * IDE에 따라 출력의 실행시간이 다르다.
 * vscode는 확장 플러그인을 거치기 때문에 오래 걸리지만 인텔리제이는 이미 최적화되어있기 때문에 실행시간이 적게 나온다.
 * vscode 4468ms
 * 인텔리제이 : 330ms
 */
public class Test {
    public static void main(String[] args) throws IOException {
        long beforeTime = System.currentTimeMillis(); // 시간 측정 시작
        
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        for (int i = 0; i < 100000; i++) {
            bw.write(i + "\n");
        }
        bw.flush(); // 버퍼를 한 번에 출력
        
        long afterTime = System.currentTimeMillis(); // 시간 측정 끝
        System.out.println("실행 시간(ms): " + (afterTime - beforeTime)); // 실행 시간 출력
    }
}
