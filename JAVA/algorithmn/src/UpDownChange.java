import java.util.Scanner;

public class UpDownChange {


    public static void main(String[] args){
        Scanner in=new Scanner(System.in);
        String next = in.next();
        String res = solution(next);
        System.out.println(res);
        in.close();
      }

    private static String solution(String next) {
        String res = "";
        for (char i : next.toCharArray()) {
            if (97<=i&& 122>=i){
                res+=Character.toString(i).toUpperCase();
            }else{
                res+=Character.toString(i).toLowerCase();
            }
        }
        return res;
    }
}
