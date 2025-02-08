package hackerRank;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Stack;

public class SimpleTextEditor {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int q = sc.nextInt();
        sc.nextLine();

        StringBuilder s = new StringBuilder();
        Stack<String> st = new Stack<>();

        for (int i = 0; i < q; i++) {
            String query = sc.nextLine();

            if (query.startsWith("1 ")) {
                st.push(s.toString());
                String temp = query.substring(2);
                s.append(temp);
            } else if (query.startsWith("2 ")) {
                st.push(s.toString());
                int temp = Integer.parseInt(query.substring(2));
                if (temp <= s.length()) {
                    s.delete(s.length() - temp, s.length());
                }
            } else if (query.startsWith("3 ")) {
                int temp = Integer.parseInt(query.substring(2));
                if (temp > 0 && temp <= s.length()) {
                    System.out.println(s.charAt(temp - 1));
                }
            } else if (query.equals("4")) {
                if (!st.isEmpty()) {
                    s = new StringBuilder(st.pop());
                }
            }
        }
        sc.close();
    }
}
