package InfrenProject.test;

import java.util.Scanner;

import Basic.math;

public class quiz2 {
    public static void main(String[] args) {
        Scanner scanner=new Scanner(System.in);
        System.out.print("어린이 키: ");
        int height=scanner.nextInt();

        String check=height>120 ? "탑승가능":"탑승불가능";

        System.out.printf("키가 %dcm 이므로 %s합니다\n",height,check);
        scanner.close();

        
    }
}
