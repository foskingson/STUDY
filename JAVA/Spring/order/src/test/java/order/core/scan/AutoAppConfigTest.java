package order.core.scan;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import order.core.AutoAppConfig;
import order.core.member.MemberService;

public class AutoAppConfigTest {
    @Test
    void basicScan(){
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AutoAppConfig.class);

        MemberService memberService = ac.getBean(MemberService.class);
     
        Assertions.assertThat(memberService).isInstanceOf(MemberService.class);
    }

}
