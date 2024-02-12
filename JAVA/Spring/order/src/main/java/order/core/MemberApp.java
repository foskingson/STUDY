package order.core;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import order.core.member.Grade;
import order.core.member.Member;
import order.core.member.MemberService;
import order.core.member.memberServiceImpl;

public class MemberApp {
    public static void main(String[] args) {
//      AppConfig appConfig = new AppConfig();
        
        // ApplicationContext라는 스프링 컨테이너를 사용한다.
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);    
        MemberService memberService = applicationContext.getBean("memberService",MemberService.class); // 스프링 컨테이너 안에서 스프링 빈 객체를 가져올 수 있다.
        

        Member member= new Member(1L, "membewr1", Grade.VIP); 
        // 접미사 없이 정수 리터럴을 지정하면 기본적으로 'int'로 간주하기 때문에 Long 유형 변수에 데이터를 입력하려면 접미사 L을 붙여줘야한다.

        memberService.join(member);
        System.out.println(member.getName());
        System.out.println(memberService.findMember(1L).getName());
    }
}
