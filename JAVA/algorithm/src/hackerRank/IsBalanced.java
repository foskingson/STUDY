package hackerRank;

import java.util.Stack;

public class IsBalanced {
    public static String isBalanced(String s) {
        Stack<Character> st = new Stack<>();

        for (char c : s.toCharArray()) {
            char temp=' ';
            if (c=='{' || c=='[' || c=='(') {
                st.add(c);
            }else if((c=='}' || c==']' || c==')') && !st.isEmpty()){
                temp = st.pop();
            }

            if(c==')' && temp!='('){
                return "NO";
            }
            if(c=='}' && temp!='{'){
                return "NO";
            }
            if(c==']' && temp!='['){
                return "NO";
            }
        }

        if(!st.isEmpty()){
            return "NO";
        }

        return "YES";
    }
}
