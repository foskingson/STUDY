package order.core.beanfind;



import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import order.core.AppConfig;
import order.core.member.MemberService;
import order.core.member.memberServiceImpl;

public class ApplicationContextBasicFindTest {
    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

    @Test 
    @DisplayName("빈 이름으로 조회")
    void findBeanByName(){
        MemberService memberService = ac.getBean("memberService",MemberService.class);
        assertThat(memberService).isInstanceOf(memberServiceImpl.class);
    }

    @Test 
    @DisplayName("타입으로만 조회")
    void findBeanByType(){
        MemberService memberService = ac.getBean(MemberService.class);
        assertThat(memberService).isInstanceOf(memberServiceImpl.class);
    }

    @Test 
    @DisplayName("구체타입으로 조회")   // 구현체에 의존하므로 좋은 코드는 아님
    void findBeanBy(){
        MemberService memberService = ac.getBean("memberService",memberServiceImpl.class);
        assertThat(memberService).isInstanceOf(memberServiceImpl.class);
    }

    @Test
    @DisplayName("빈 이름으로 조회 안나올경우")
    void findBeanByNameX(){
        //MemberService xxxx = ac.getBean("xxxx",MemberService.class);

        assertThrows(NoSuchBeanDefinitionException.class, 
            ()->  ac.getBean("xxxx",MemberService.class)
        );
    }
}
