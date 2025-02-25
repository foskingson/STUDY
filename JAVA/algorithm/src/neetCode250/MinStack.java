package neetCode250;

import java.util.Stack;

public class MinStack {
    Stack<Integer> s;
    Stack<Integer> minSt;

    public MinStack() {
        s = new Stack<>();    
        minSt = new Stack<>();
    }
    
    public void push(int val) {
        s.push(val);
        if (minSt.isEmpty() || val <= minSt.peek()) {
            minSt.push(val);
        }
    }
    
    public void pop() {
        int pop = s.pop();
        if (pop == minSt.peek()) {
            minSt.pop();
        }
    }
    
    public int top() {
        return s.peek();
    }
    
    public int getMin() {
        return minSt.peek();
    }
}
