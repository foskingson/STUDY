package order.core.order;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import order.core.AppConfig;
import order.core.member.Grade;
import order.core.member.Member;
import order.core.member.MemberService;

public class OrderServiceTest {
    MemberService memberService;
    OrderService orderService;

    @BeforeEach
    public void beforeEach(){
        AppConfig appConfig = new AppConfig();
        memberService = appConfig.memberService();
        orderService = appConfig.orderService();
    }

    @Test
    void 주문생성(){
        Member member = new Member(1L, "하하하", Grade.VIP);
        memberService.join(member);

        Order order = orderService.creatOrder(1L, "고래밥", 5000);

        Assertions.assertThat(order.getDiscountPrice()).isEqualTo(500);
    }

    

}
