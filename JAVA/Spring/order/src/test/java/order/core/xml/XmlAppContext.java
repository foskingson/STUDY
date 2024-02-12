package order.core.xml;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

import order.core.member.Member;
import order.core.member.MemberService;

public class XmlAppContext {
    @Test
    void xmlAppContext(){
        ApplicationContext ac = new GenericXmlApplicationContext("appConfig.xml");  //최근에는 잘 안쓰지만 xml파일로도 구성정보로 줄 수 있다.
        MemberService memberService = ac.getBean("memberService",MemberService.class);

        Assertions.assertThat(memberService).isInstanceOf(MemberService.class);
    }
}
