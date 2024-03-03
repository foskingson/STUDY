package InfrenProject.test;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class Quiz13 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in,"MS949");

        try(BufferedWriter writer = new BufferedWriter(new FileWriter("saying.txt"))) {
            writer.write("세 살 __ 여든 까지 간다\n");
            writer.write("버릇\n");
            writer.write("소 잃고 __ 고친다\n");
            writer.write("외양간\n");
            writer.write("천 리 길도 한 __부터\n");
            writer.write("걸음\n");
        } catch (Exception e) {
            System.out.println("파일을 읽거나 쓰는 도중 오류가 발생했습니다.");
            e.printStackTrace();
        }

        try(BufferedReader reader =new BufferedReader(new FileReader("saying.txt"))) {
            ArrayList<String> quiz = new ArrayList<String>();
            ArrayList<String> ans = new ArrayList<String>();
            int lineNum=1;
            String temp;
            String line;

            while((line = reader.readLine())!=null){
                if(lineNum%2==1){
                    quiz.add(line);
                }
                else{
                    ans.add(line);
                }
                lineNum++;
            }

            System.out.println("속담 퀴즈 빈칸에 알맞은 말을 입력");
            System.out.println("----------------------------------------------------------");
            for(int i=0;i<quiz.size();i++){
                System.err.println("문제) " + quiz.get(i));
                System.out.print("정답 : ");
                temp = scanner.next();
                if(temp.equals(ans.get(i))){
                    System.out.println("정답!!");
                }else{
                    System.err.println("틀렸습니다. 정답은 " + ans.get(i)+"입니다.");
                }
                System.out.println();
                System.out.println();
            }
        } catch (Exception e) {
            System.out.println("파일을 읽거나 쓰는 도중 오류가 발생했습니다.");
            e.printStackTrace();
        }    

        scanner.close();
    }
    
}
