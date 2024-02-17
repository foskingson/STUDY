package InfrenProject.test;

import java.util.Scanner;

public class Quiz6 {
    public static String getHiddenData(String data,int num){
        int count=data.length()-num;
        return data.substring(0,num)+"*".repeat(count);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in, "MS949");
        System.out.print("이름 : ");
        String name = scanner.nextLine();

        System.out.print("주민등록번호 : ");
        String regNum = scanner.nextLine();

        System.out.print("전화번호 : ");
        String phoneNum = scanner.nextLine();

        System.out.println("이름 : " + getHiddenData(name,1));
        System.out.println("주민등록번호 : " + getHiddenData(regNum,8));
        System.out.println("전화번호 : " + getHiddenData(phoneNum,9));

        scanner.close();
    }
}
