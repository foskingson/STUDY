package order.core.singleton;

import static org.assertj.core.api.Assertions.assertThat;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import order.core.AppConfig;
import order.core.member.MemberService;

public class SingletonTest {

    @Test
    @DisplayName("스프링 없는 순수한 DI컨테이너")
    void 순수DI컨테이너(){
        AppConfig appConfig = new AppConfig();
        
        MemberService memberService1 = appConfig.memberService();
        MemberService memberService2 = appConfig.memberService();

        System.out.println(memberService1);
        System.out.println(memberService2);

        assertThat(memberService1).isNotSameAs(memberService2);


    }
    

    @Test
    @DisplayName("싱글톤 패턴을 적용한 객체 사용")
    void 싱글톤서비스테스트(){
        SingletonService singletonService1 = SingletonService.getInstance();
        SingletonService singletonService2 = SingletonService.getInstance();

        //new를 통한 서로 다른 객체가 생기는 것이 아닌 만들어진 하나의 객체 인스턴스를 불러와 사용한다.
        System.out.println("single1: " +singletonService1);
        System.out.println("single2: "+singletonService2);
        assertThat(singletonService1).isSameAs(singletonService2);

    }

    @Test
    @DisplayName("스프링 컨테이너와 싱글톤")
    void 싱글톤스프링컨테이너(){
        AnnotationConfigApplicationContext appConfig = new AnnotationConfigApplicationContext(AppConfig.class);
        
        MemberService memberService1 = appConfig.getBean("memberService",MemberService.class);
        MemberService memberService2 = appConfig.getBean("memberService",MemberService.class);

        System.out.println(memberService1);
        System.out.println(memberService2);

        assertThat(memberService1).isSameAs(memberService2);

    }
}
