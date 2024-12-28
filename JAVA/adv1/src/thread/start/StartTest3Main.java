package thread.start;

import static util.MyLogger.log;

public class StartTest3Main {
    public static void main(String[] args) {
        Runnable r = new Runnable() {
            @Override
            public void run() {
                for (int i = 1; i <6 ; i++) {
                    log("value: "+i);
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        };
        Thread t = new Thread(r,"counter");
        t.start();
    }
}
