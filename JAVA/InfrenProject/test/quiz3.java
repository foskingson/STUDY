package InfrenProject.test;

import java.util.Scanner;

public class Quiz3 {
    public static void main(String[] args) {
        Scanner scanner=new Scanner(System.in);

        System.out.print("주민등록번호: ");
        String birth= scanner.nextLine();

        String first= birth.substring(0,birth.indexOf("-")+2);
        String second= birth.substring(birth.indexOf("-")+2);
        System.out.println(first);
        System.out.println(second);
        


        scanner.close();
    }
}
