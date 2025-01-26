package neetCode250;

import java.util.Stack;

public class IsValid {
    public boolean isValid(String s) {
        Stack<Character> st = new Stack<>();

        for (char c : s.toCharArray()) {
            if (c=='(' || c=='{' || c=='[') {
                st.push(c);
            }else if((c==')' || c=='}' || c==']') && st.isEmpty()){
                return false;
            }
            
            if(c==')' && st.pop()!='('){
                return false;
            }else if(c=='}' && st.pop()!='{'){
                return false;
            }else if(c==']' && st.pop()!='['){
                return false;
            }
        }
        
        return st.isEmpty();
    }
}
