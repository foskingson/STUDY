package Basic;  // 동일한 패키지(폴더) 안에서는 import문없이 다른파일의 패키지에 접근 가능

public class varAPP {
    public static void main(String[] args) {
        int a = 1; // Number -> integer  ... -2, -1 , 0, 1, 2 ...
        System.out.println(a);
         
        double b = 1.1; // real number -> double ... -2.0, -1.0, 0, 1.0, 2.0 ...
        System.out.println(Math.floor(b));
         
        String c = "Hello World";   // 문자열은 스트링
        char d = 'h';   // 문자는 char
        System.out.println(c);
        System.out.println(d+"d");
    }
}
