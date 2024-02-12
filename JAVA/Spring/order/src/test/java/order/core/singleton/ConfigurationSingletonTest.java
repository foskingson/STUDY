package order.core.singleton;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import order.core.AppConfig;
import order.core.member.MemberService;
import order.core.member.memberServiceImpl;
import order.core.order.OrderService;
import order.core.order.OrderServiceImpl;

public class ConfigurationSingletonTest {
    @Test
    void configuration(){
        ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

        memberServiceImpl bean1 = ac.getBean("memberService", memberServiceImpl.class);
        OrderServiceImpl bean2 = ac.getBean("orderService",OrderServiceImpl.class);

        System.out.println(bean1.getMemberRepository());
        System.out.println(bean2.getMemberRepository());
        Assertions.assertThat(bean1.getMemberRepository()).isEqualTo(bean2.getMemberRepository());
    }

    @Test
    void configurationDepp(){
        ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

        AppConfig bean = ac.getBean(AppConfig.class);
        System.out.println(bean);
    }
}
