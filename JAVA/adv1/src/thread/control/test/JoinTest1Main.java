package thread.control.test;
import static util.MyLogger.log;
import static util.ThreadUtils.sleep;

// 각각 스레드 차례대로 1 2 3이 한줄씩 로그로 나옴
// 하나의 스레드가 끝나기 전까지 join으로 main에서 기다리기 때문에 다음 스레드의 start를 하지 않음
public class JoinTest1Main {
    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(new MyTask(), "t1");
        Thread t2 = new Thread(new MyTask(), "t2");
        Thread t3 = new Thread(new MyTask(), "t3");
        t1.start();
        t1.join();
        t2.start();
        t2.join();
        t3.start();
        t3.join();
        System.out.println("모든 스레드 실행 완료");
    }
    static class MyTask implements Runnable {
        @Override
        public void run() {
            for (int i = 1; i <= 3; i++) {
                log(i);
                sleep(1000);
            }
        }
    }
}