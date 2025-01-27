package hackerRank;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class BalancedOrNot {
    public static List<Integer> balancedOrNot(List<String> expressions, List<Integer> maxReplacements) {
        List<Integer> res = new ArrayList<>();

        for (int i = 0; i < expressions.size(); i++) {
            res.add(checkBal( expressions.get(i),maxReplacements.get(i)));
        }

        return res;
    }

    private static int checkBal(String exp, int replace) {
        Stack<Character> st = new Stack<>();
        for (char c : exp.toCharArray()) {
           if (c=='<') {
                st.push(c);
                continue;
           }else if(c=='>' && st.isEmpty()){
                if (replace>0) {
                    replace--;
                    continue;
                }else{
                    return 0;
                }
           }
           st.pop();
        }

        if (!st.isEmpty()) {
           return 0;
        }

        return 1;
    }

    public static void main(String[] args) {
        balancedOrNot(List.of("<<<><><>"), List.of(2));
    }
}
