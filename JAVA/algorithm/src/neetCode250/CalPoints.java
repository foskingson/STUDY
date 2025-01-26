package neetCode250;

import java.util.Stack;

public class CalPoints {
    public int calPoints(String[] operations) {
        Stack<Integer> s = new Stack<>();

        for (String op : operations) {
            if (op.equals("+")) {
                if (s.size()>=2) {
                    s.push(s.get(s.size()-1) + s.get(s.size()-2));
                }
            }else if(op.equals("D")){
                if (!s.isEmpty()) {
                    s.push(s.peek()*2);
                }
            }else if(op.equals("C")  && !s.isEmpty()){
                if (!s.isEmpty()) {
                    s.pop();
                }
            }else{
                s.push(Integer.parseInt(op));
            }
        }

        int sum = 0;
        for (int i : s) {
            sum+=i;
        }
        return sum;
    }

    public static void main(String[] args) {
        CalPoints calPoints = new CalPoints();
        System.out.println(calPoints.calPoints(new String[]{"5","2","C","D","+"}));
    }
}
