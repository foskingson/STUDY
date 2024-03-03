import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.IntStream;


public class Fos { 
    public static void main(String[] args) throws InterruptedException {
        BankAccount account = new BankAccount(1000);

        // 여러 스레드에서 계좌에 동시에 접근
        Thread thread1 = new Thread(() -> {
            account.deposit(500);
        });

        Thread thread2 = new Thread(() -> {
            account.withdraw(200);
        });

        thread1.start();
        thread2.start();

        // 잔액 출력
        System.out.println("Current balance: " + account.getBalance());
    }
}

class BankAccount {
    private int balance;

    public BankAccount(int balance) {
        this.balance = balance;
    }

    public synchronized void deposit(int amount) {
        balance += amount;
    }

    public synchronized void withdraw(int amount) {
        if (balance >= amount) {
            balance -= amount;
        } else {
            System.out.println("Insufficient balance!");
        }
    }

    public synchronized int getBalance() {
        return balance;
    }
}