package hackerRank;

import java.util.*;

public class Solve {
    static class Element {
        int idx, val;

        Element(int idx, int val) {
            this.idx = idx;
            this.val = val;
        }
    }

    public static List<Integer> solve(List<Integer> arr, List<Integer> queries) {
        List<Integer> result = new ArrayList<>();

        for (int q : queries) {
            result.add(findMinOfMaxSlidingWindow(arr, q));
        }
        return result;
    }

    private static int findMinOfMaxSlidingWindow(List<Integer> arr, int k) {
        Deque<Element> deque = new LinkedList<>();
        List<Integer> maxArr = new ArrayList<>();

        for (int i = 0; i < arr.size(); i++) {
            if (!deque.isEmpty() && deque.peekFirst().idx == i - k) {
                deque.pollFirst();
            }
            while (!deque.isEmpty() && deque.peekLast().val <= arr.get(i)) {
                deque.pollLast();
            }
            deque.addLast(new Element(i, arr.get(i)));

            if (i >= k - 1) {
                maxArr.add(deque.peekFirst().val);
            }
        }

        return Collections.min(maxArr);
    }

    public static void main(String[] args) {
        System.out.println(solve(List.of(2, 3, 4, 5, 6), List.of(2, 3))); 
    }
}
