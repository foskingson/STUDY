package neetCode250;

import java.util.LinkedList;
import java.util.Queue;

class MyStack {

    Queue<Integer> q;
    public MyStack() {
        q= new LinkedList<>();
    }
    
    public void push(int x) {
        if (!q.isEmpty()) {
            Queue<Integer> temp = new LinkedList<>();
            temp.add(x);
            temp.addAll(q);
            q=temp;
        }else{
            q.add(x);
        }
    }
    
    public int pop() {
        return q.poll();
    }
    
    public int top() {
        return q.peek();
    }
    
    public boolean empty() {    
        return q.isEmpty();
    }
}

/**
 * Your MyStack object will be instantiated and called as such:
 * MyStack obj = new MyStack();
 * obj.push(x);
 * int param_2 = obj.pop();
 * int param_3 = obj.top();
 * boolean param_4 = obj.empty();
 */