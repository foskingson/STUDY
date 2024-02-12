package order.core.discount;


import static org.assertj.core.api.Assertions.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import order.core.member.Grade;
import order.core.member.Member;


public class RateDiscountPolicyTest {
    
    @Test
    @DisplayName("VIP는 10% 할인")
    void VIP할인가격() {

        DiscountPolicy discoutPolicy = new RateDiscountPolicy();
        Member member = new Member(1L, "a", Grade.VIP);

        int res = discoutPolicy.discount(member, 20000);

        assertThat(res).isEqualTo(2000);

        
    }

    @Test
    @DisplayName("BASIC은 할인 없음")
    void BASIC할인가격() {

        DiscountPolicy discoutPolicy = new RateDiscountPolicy();
        Member member = new Member(1L, "a", Grade.BASIC);

        int res = discoutPolicy.discount(member, 20000);

        assertThat(res).isEqualTo(0);
    }




}
