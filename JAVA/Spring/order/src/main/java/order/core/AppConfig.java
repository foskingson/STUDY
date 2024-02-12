package order.core;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import order.core.discount.DiscountPolicy;
import order.core.discount.FixDiscountPolicy;
import order.core.discount.RateDiscountPolicy;
import order.core.member.MemberRepository;
import order.core.member.MemberService;
import order.core.member.MemoryMemberRepository;
import order.core.member.memberServiceImpl;
import order.core.order.OrderService;
import order.core.order.OrderServiceImpl;

// 설정파일을 통해 객체지향의 원칙인 DIP를 지키도록 도와준다.
@Configuration
public class AppConfig {

    @Bean
    public MemberService memberService(){
        return new memberServiceImpl(memberRepository());
    }

    @Bean
    public MemberRepository memberRepository() {
        return new MemoryMemberRepository();
    }

    @Bean
    public OrderService orderService(){
        return new OrderServiceImpl(memberRepository(), discountPolicy());
    }

    @Bean
    public DiscountPolicy discountPolicy() {
        return new RateDiscountPolicy();
    }



    
}
