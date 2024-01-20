public class bool {
    public static void main(String[] args) {
        // == 라는 비교연산자는 각각 참조하는곳이 같은지 확인하는 연산자이다.
        // equals라는 메서드는 내용(값)이 같은지 비교할 수 있다.
        // 정수나 문자와 같은 원시데이터를 비교할때는 ==(동등비교연산자)를 사용하고 그게 아닌 배열과 같은 데이터를 비교할때는 equals를 사용한다
        // 다만 new를 사용하지 않는 문자열은 동등비교연산자를 사용한다 
        int a1=1;
        int a2=1;

        String b1="hello";
        String b2="hello";

        String c1=new String("c");  // 인스턴스로 만들어진 스트링은 값이 갔더라도 참조하는 곳이 다르기때문에 ==로는 거짓이 나온다.
        String c2=new String("c");

        if(a1==a2){
            System.out.println("숫자 같음");
        }

        if(b1==b2){
            System.out.println("문자 같음");
        }

        if(c1==c2){
            System.out.println("CCCC");
        }

        if(c1.equals(c2)){
            System.out.println("CCC");
        }
    }
    
}
