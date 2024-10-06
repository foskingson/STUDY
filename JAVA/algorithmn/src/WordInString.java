import java.util.Scanner;

public class WordInString {
  public static void main(String[] args){
    Scanner in=new Scanner(System.in);
    String input1 = in.nextLine();
    String res = solution(input1);
    System.out.println(res);
    in.close();
  }

  private static String solution(String input1) {
    String res="";
    String temp="";
    for (char i : input1.toCharArray()) {
      if(i == ' '){
        if (res.length()<temp.length()) {
          res = temp;
        } temp ="";
      }else{
        temp+=i;
      }
    }

    if (res.length()<temp.length()) {
      res = temp;
    }
    return res;
  }

}
