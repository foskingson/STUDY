package thread.start;

public class HelloThreadMain {
    public static void main(String[] args) throws InterruptedException {
        System.out.println(Thread.currentThread().getName() + ": main() start");
        HelloThread helloThread = new HelloThread();
        helloThread.start();
        Thread.sleep(100);
        System.out.println(Thread.currentThread().getName() + ": main() end");
    }
}
