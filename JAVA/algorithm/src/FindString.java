import java.util.Scanner;

public class FindString {
    public static void main(String[] args){
        Scanner in = new Scanner(System.in);
        String input1 = in.next();
        String input2 = in.next();
  
        char check = input2.charAt(0);
        int res = solution(input1,check);
        System.out.println(res);
        in.close();
      }

    public static int solution(String input1, char check) {
      int count=0;
      if (97<=check && 122>=check) {
        input1 = input1.toLowerCase();
      }else{
        input1 = input1.toUpperCase();
      }
      
      for(int i=0;i<input1.length();i++){
        if (input1.charAt(i)==check) {
          count++;
        }
      }
      return count;
    }
}
