package order.core.member;


import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import order.core.AppConfig;


public class memberServicTest {

    MemberService memberService;

    @BeforeEach
    public void beforeEach(){
        AppConfig appConfig = new AppConfig();
        memberService = appConfig.memberService();
    }


    @Test
    void 회원가입(){
        Member member= new Member(1L, "member1", Grade.VIP); 
        memberService.join(member);

        Member findMember= memberService.findMember(1L);

        Assertions.assertThat(member).isEqualTo(findMember);

    }
}
