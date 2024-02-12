package order.core.order;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import order.core.discount.RateDiscountPolicy;
import order.core.member.Grade;
import order.core.member.Member;
import order.core.member.MemberRepository;
import order.core.member.MemoryMemberRepository;

public class OrderServiceImplTest {
    @Test
    void creatOrder(){
        MemoryMemberRepository memberRepository = new MemoryMemberRepository();
        memberRepository.save(new Member(1L, "2222", Grade.VIP));
        OrderServiceImpl orderService = new OrderServiceImpl(memberRepository, new RateDiscountPolicy() );
        Order res = orderService.creatOrder(1L, "aaaa", 10000);

        Assertions.assertThat(res.getDiscountPrice()).isEqualTo(1000);
    }
}

