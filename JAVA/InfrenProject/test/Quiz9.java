package InfrenProject.test;

import java.util.ArrayList;

class Student{
    public String name;
    public String info;

    public Student(String name, String info) {
        this.name = name;
        this.info = info;
    }

    

}

public class Quiz9 {
    public static void main(String[] args) {
        ArrayList<Student> repo = new ArrayList<>();
        repo.add(new Student("유재석", "파이썬"));
        repo.add(new Student("박명수", "자바"));
        repo.add(new Student("김종국", "자바"));
        repo.add(new Student("조세호", "C"));
        repo.add(new Student("서장훈", "파이썬"));

        System.out.println("자바 자격증 보유 학생");
        System.out.println("======================");
        for (Student student : repo) {
            if(student.info.equals("자바")){
                System.out.println(student.name);
            }    
        }
    }
}
