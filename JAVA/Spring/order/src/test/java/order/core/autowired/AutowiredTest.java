package order.core.autowired;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import io.micrometer.common.lang.Nullable;
import order.core.member.Member;

public class AutowiredTest {
    @Test
    void AutowiredOption(){
        ApplicationContext ac = new AnnotationConfigApplicationContext(TestBean.class);
    }

    static class TestBean{

        @Autowired(required = false)
        public void setNoBean1(Member noBean1){
            System.out.println("nobean1: "+ noBean1);
        }

        @Autowired
        public void setNoBean2(@Nullable Member noBean1){
            System.out.println("nobean2: "+ noBean1);
        }

        @Autowired
        public void setNoBean3(Optional<Member> noBean1){
            System.out.println("nobean3: "+ noBean1);
        }
    }
}
