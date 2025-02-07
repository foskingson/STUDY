package hackerRank;

import java.util.Scanner;
import java.util.Stack;

public class QueueUsingTwoStacks {
    public static void main(String[] args) {
        Stack<Integer> s1 = new Stack<>();
        Stack<Integer> s2 = new Stack<>();

        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();

        for (int i = 0; i < n; i++) {
            int q = sc.nextInt();

            if (q == 1) {
                int num = sc.nextInt();
                s1.push(num);
            } else if (q == 2) {
                deleteFront(s1, s2);
            } else {
                peekFront(s1, s2);
            }
        }
    }

    private static void peekFront(Stack<Integer> s1, Stack<Integer> s2) {
        if (s2.isEmpty()) {
            while (!s1.isEmpty()) {
                s2.push(s1.pop());
            }
        }
        if (!s2.isEmpty()) {
            System.out.println(s2.peek());
        }
    }

    private static void deleteFront(Stack<Integer> s1, Stack<Integer> s2) {
        if (s2.isEmpty()) {
            while (!s1.isEmpty()) {
                s2.push(s1.pop());
            }
        }
        if (!s2.isEmpty()) {
            s2.pop();
        }
    }
}
