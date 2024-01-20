package Basic;

public class math {
    public static void main(String[] args) {
        System.out.println(6 + 2); // 8
        System.out.println(6 - 2); // 4
        System.out.println(6 * 2); // 12
        System.out.println(6 / 2); // 3
        System.out.println(7 % 2); // 1

        // Math라는 클래스는 수학연산을 위한 메서드나 상수를 제공한다
        System.out.println(Math.PI);    
        System.out.println(Math.floor(3.6));    // 소수의 뒷부분을 짤라 정수로 만드는 메서드
        System.out.println(Math.ceil(3.2)); // 소수의 뒷자리를 올려 +1 상태인 정수로 만드는 메서드
    }
}
