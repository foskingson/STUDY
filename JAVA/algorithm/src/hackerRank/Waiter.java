package hackerRank;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Waiter {
    public static List<Integer> waiter(List<Integer> number, int q) {
        List<Integer> res = new ArrayList<>();
        List<Integer> p = createPrime(q);
        Stack<Integer> a = new Stack<>();
        a.addAll(number);

        for (int i = 0; i < q; i++) {
            Stack<Integer> b = new Stack<>();
            Stack<Integer> nextA = new Stack<>();

            while (!a.isEmpty()) {
                int val = a.pop();
                if (val % p.get(i) == 0) {
                    b.add(val);
                } else {
                    nextA.add(val);
                }
            }

            while (!b.isEmpty()) {
                res.add(b.pop());
            }

            a = nextA;
        }

        while (!a.isEmpty()) {
            res.add(a.pop());
        }

        return res;
    }

    private static List<Integer> createPrime(int q) {
        List<Integer> p = new ArrayList<>();
        int k = 2;
        while (p.size() != q) {
            boolean check = true;
            for (int i = 2; i <= Math.sqrt(k); i++) {
                if (k % i == 0) {
                    check = false;
                    break;
                }
            }
            if (check) {
                p.add(k);
            }
            k++;
        }
        return p;
    }
}
