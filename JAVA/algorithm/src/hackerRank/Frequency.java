package hackerRank;


import java.util.Arrays;
import java.util.List;
import java.util.Stack;

public class Frequency {
    public static List<Integer> frequency(String s) {
        Integer[] temp = new Integer[26];
        Arrays.fill(temp,0);
        Stack<Character> st = new Stack<>();
        StringBuilder sb = new StringBuilder();

        for (char c : s.toCharArray()) {
            st.push(c);
        }

        while (!st.isEmpty()) {
            char c = st.pop();
            int q = 0;

            if (c == ')') {
                char check = st.pop();  
                int digit = 1;  
            
                while (check != '(') {
                    q = q + (check - '0') * digit;  
                    digit *= 10;  
                    check = st.pop();  
                }

                c = st.pop();
            }

            if (q==0) {
                q=1;
            }

            if (c=='#') {
                int num1 = st.pop()-'0';        // 일의자리
                int num2 = (st.pop()-'0')*10;   // 십의자리
                temp[num1+num2-1]+=q;
            }else{
                temp[c-'0'-1]+=q;
            }
        }

        return Arrays.asList(temp);
    }  


    public static void main(String[] args) {
        frequency("1(2)23(3)");
    }
}
