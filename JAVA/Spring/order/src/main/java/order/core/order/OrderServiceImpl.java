package order.core.order;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import order.core.discount.DiscountPolicy;
import order.core.discount.FixDiscountPolicy;
import order.core.discount.RateDiscountPolicy;
import order.core.member.Member;
import order.core.member.MemberRepository;
import order.core.member.MemoryMemberRepository;

@Component
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final MemberRepository memberRepository;
    private final DiscountPolicy discountPolicy;


    @Override
    public Order creatOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId);
        int discountPrice = discountPolicy.discount(member, itemPrice);
        return new Order(memberId,itemName,itemPrice,discountPrice);
    }
    
    public MemberRepository getMemberRepository(){
        return memberRepository;
    }
}
