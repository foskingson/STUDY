import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Backjoon1541 {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        String line = bf.readLine();
        solution(line);
    }

    private static void solution(String line) {
        int check =0;
        Stack<Integer> num = new Stack<>();
        Stack<Character> sign = new Stack<>();

        char[] form = line.toCharArray();
        for (int i = 0; i < form.length; i++) {
            if (form[i]=='+' || form[i]=='-') {
                num.add(Integer.parseInt(line.substring(check, i)));
                sign.add(form[i]);
                check=i+1;
            }
        }
        num.add(Integer.parseInt(line.substring(check, line.length())));

        int sum = 0;
        int tempSum = num.pop();
        while (!num.isEmpty()) {
            char checkSign = sign.pop();
            int temp = num.pop();
            if (checkSign=='+') {
                tempSum+=temp;
            }else{
                sum-=tempSum;
                tempSum=temp;
            }
        }

        if (tempSum!=0) {
            sum+=tempSum;
        }
        System.out.println(sum);

    }
}
