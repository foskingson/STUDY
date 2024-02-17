package InfrenProject.test;

import java.util.Scanner;

public class Quiz4 {
    public static void main(String[] args) {
        Scanner scanner=new Scanner(System.in);
        System.out.println("입력하세요 : 일반차량: 1 / 경차 혹은 장애인차량: 2 ");
        int car = scanner.nextInt();
        System.out.print("주차 시간 : ");
        int hour = scanner.nextInt();
        int sum= hour*4000;

        

        switch (car) {
            case 1:
                if(sum>30000){
                    sum=30000;
                }
                System.out.println("주차 요금은 "+sum+"원 입니다.");
                break;
            case 2:
                sum/=2;
                if(sum>30000){
                    sum=30000;
                }
                System.out.println("주차 요금은 "+sum+"원 입니다.");
                break;
            default:
                break;
        }
    }
}
