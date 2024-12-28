package thread.start;

import static util.MyLogger.log;

public class StartTest2Main {
    public static void main(String[] args) {
        Test2Runnable r = new Test2Runnable();
        Thread t = new Thread(r,"counter");
//        t.setName("counter");
        t.start();
    }

    static class Test2Runnable implements Runnable{
        @Override
        public void run(){
            for (int i = 1; i <6 ; i++) {
                log("value: "+i);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
}
