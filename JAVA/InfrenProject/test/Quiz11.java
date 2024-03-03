package InfrenProject.test;

public class Quiz11 {
    public static void main(String[] args) {
        int errorCode=2;

        try{
            if(errorCode==0){
                System.out.println("상품 구매 완료");
            } else if(errorCode==1){
                throw new TimeOutException("시간이 마감되었습니다.");
            }else{
                throw new NotObjectException("품절되었습니다.");
            }
        }catch(TimeOutException e){
            System.out.println(e.getMessage());
        }catch(NotObjectException e){
            System.out.println(e.getMessage());
        }

    }
}

class TimeOutException extends Exception {
    public TimeOutException(String message) {
        super(message);
    }
}

class NotObjectException extends Exception {
    public NotObjectException(String message) {
        super(message);
    }
}