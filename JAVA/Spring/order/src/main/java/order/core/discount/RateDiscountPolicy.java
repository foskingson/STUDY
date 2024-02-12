package order.core.discount;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import order.core.member.Grade;
import order.core.member.Member;

@Primary
@Component
public class RateDiscountPolicy implements DiscountPolicy {
    private int discountPercent=10;

    @Override
    public int discount(Member member, int price) { 
        if(member.getGrade()==Grade.VIP){
            return price*discountPercent/100;
        }else{
            return 0;
        }
    }
}
