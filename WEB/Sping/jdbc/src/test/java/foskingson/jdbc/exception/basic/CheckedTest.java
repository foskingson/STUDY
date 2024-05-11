package foskingson.jdbc.exception.basic;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.Test;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CheckedTest{   
    @Test
    void checked_catch(){
        Service service = new Service();
        service.callCatch();
    }

    @Test
    void checked_throw(){
        Service service = new Service();
        assertThatThrownBy(() -> service.callThrow())
        .isInstanceOf(MyCheckedException.class);   
    }

    static class MyCheckedException extends Exception{  // Excetion을 상복받은 예외는 체크 예외
        public MyCheckedException(String message){
            super(message);
        }
    }

    static class Service{
        Repository repository = new Repository();
        public void callCatch(){
            try {
                repository.call();
            } catch (MyCheckedException e) {
                log.info("예외처리 message={}",e.getMessage(),e);
            }
        }

        public void callThrow() throws MyCheckedException{
            repository.call();
        }
    }

    static class Repository{
        public void call() throws MyCheckedException{
            throw new MyCheckedException("ex"); // 던지는걸 위에 무조건 선언해줘야함
        }
    }
}
