package foskingson.jdbc.exception.basic;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.Test;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class UncheckedTest {

    @Test
    void unchecked_catch(){
        Service service = new Service();
        service.callCatch();
    }

    @Test
    void unchecked_throw(){
        Service service = new Service();
        assertThatThrownBy(() -> service.callThrow())
        .isInstanceOf(MyUncheckedException.class);   
    }

    static class MyUncheckedException extends RuntimeException{
        public MyUncheckedException(String m){
            super(m);
        }
    }

    static class Service{
        Repository repository = new Repository();
        public void callCatch(){
            try {
                repository.call();
            } catch (MyUncheckedException e) {
                log.info("예외 처리 message={}",e.getMessage(),e);
            }
        }

        public void callThrow(){
            repository.call();
        }
    }

    static class Repository{
        public void call(){
            throw new MyUncheckedException("ex"); // 던지는걸 위에 무조건 선언해줘야함
        }
    }
}