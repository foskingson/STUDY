package hackerRank;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class GetMinCost {

    public static int getMinCost(List<Integer> cost, List<Integer> time) {
        List<Task> temp = new ArrayList<>();

        for (int i = 0; i < cost.size(); i++) {
            temp.add(new Task(cost.get(i), time.get(i)));
        }

        Collections.sort(temp, (a, b) -> a.c - b.c);
        Deque<Task> tasks = new LinkedList<>();
        for (Task task : temp) {
            tasks.add(task);
        }

        int res = 0;
        int free = 2;
        int money = 2;

        while (!tasks.isEmpty()) {
            Task task = tasks.pollFirst();

            if (money==2) {
                money--;
                res+=task.c;
                
            }
            
        }

        return res;
    }

    static class Task {
        int c;
        int t;

        public Task(int c, int t) {
            this.c = c;
            this.t = t;
        }
    }

    public static void main(String[] args) {
        List<Integer> cost = List.of(1, 1, 3, 4);
        List<Integer> time = List.of(3, 1, 2, 3);

        System.out.println(getMinCost(cost, time));
    }
}
