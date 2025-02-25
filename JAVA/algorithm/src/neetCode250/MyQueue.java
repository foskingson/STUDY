package neetCode250;

import java.util.Stack;

public class MyQueue {
    Stack<Integer> s;

    public MyQueue() {
        s=new Stack<>();    
    }
    
    public void push(int x) {
        if (!s.isEmpty()) {
            Stack<Integer> temp = new Stack<>();
            temp.add(x);
            temp.addAll(s);
            s=temp;
        }else{
            s.add(x);
        }
    }
    
    public int pop() {
        return s.pop();
    }
    
    public int peek() {
        return s.peek();
    }
    
    public boolean empty() {
        return s.isEmpty();
    }
}
