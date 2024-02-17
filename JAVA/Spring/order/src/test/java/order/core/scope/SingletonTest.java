package order.core.scope;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;

public class SingletonTest {

    @Test
    void singletonBeanTest(){
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(SingletonBean.class);
        System.out.println("find prototypeBean1");
        SingletonBean bean1 = ac.getBean(SingletonBean.class);
        System.out.println("find prototypeBean2");
        SingletonBean bean2 = ac.getBean(SingletonBean.class);

        System.out.println("1: "+bean1);
        System.out.println("2: "+bean2);

        Assertions.assertThat(bean1).isSameAs(bean2);
        ac.close();
    }

    @Scope("singleton")
    static class SingletonBean{
        @PostConstruct
        public void init(){
            System.out.println("SingletonTest.SingletonBean.init()");
        }

        @PreDestroy
        public void destroy(){
            System.out.println("SingletonTest.SingletonBean.destroy()");
        }
    }
}
