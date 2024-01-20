import java.util.Scanner;

import javax.swing.JOptionPane;

public class App {
    public static void main(String[] args) throws Exception {
        
        Scanner scanner=new Scanner(System.in);

        System.out.print("공급가: ");   //print : 출력후 줄바꿈없음
        int supply=scanner.nextInt();       // 정수 입력

        System.out.print("제작비용: ");
        int prodCost=scanner.nextInt(); 

        System.out.print("동업자수 :");
        int workPe=scanner.nextInt();

        scanner.close();

        System.out.println("\n");
        System.out.println("VAT :"+(supply*0.1));
        System.out.println("Total :"+(supply*0.1+supply));
        System.out.println("income :"+(supply-prodCost));
        System.out.println("Dividend : "+((supply-prodCost)/workPe));
        //String b=JOptionPane.showInputDialog("공급가: ");

    }
}
