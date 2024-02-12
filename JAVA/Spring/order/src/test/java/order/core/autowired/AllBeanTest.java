package order.core.autowired;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import order.core.AutoAppConfig;
import order.core.discount.DiscountPolicy;
import order.core.member.Grade;
import order.core.member.Member;

public class AllBeanTest {
    
    @Test
    void findAllBean(){
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AutoAppConfig.class,DiscountService.class);

        DiscountService discountService = ac.getBean(DiscountService.class);
        Member member = new Member(1L, "A", Grade.VIP);
        int res = discountService.discount(member,10000,"fixDiscountPolicy");

        assertThat(discountService).isInstanceOf(DiscountService.class);
        assertThat(res).isEqualTo(1000);
    }


    static class DiscountService{
        private final Map<String,DiscountPolicy> policyMap;
        private final List<DiscountPolicy> policies;

        @Autowired
        public DiscountService(Map<String, DiscountPolicy> policyMap, List<DiscountPolicy> policies) {
            this.policyMap = policyMap;
            this.policies = policies;
            System.out.println("PolicyMap: "+ policyMap);
            System.out.println("Polices: "+ policies);
        }

        public int discount(Member member, int price,String code){
            DiscountPolicy discountPolicy = policyMap.get(code);
            return discountPolicy.discount(member, price);
           
        }
    }
        
    }

    
