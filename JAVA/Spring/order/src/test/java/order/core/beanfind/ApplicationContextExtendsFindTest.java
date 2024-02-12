package order.core.beanfind;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Map;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoUniqueBeanDefinitionException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import order.core.AppConfig;
import order.core.discount.DiscountPolicy;
import order.core.discount.FixDiscountPolicy;
import order.core.discount.RateDiscountPolicy;
import order.core.member.MemberRepository;

public class ApplicationContextExtendsFindTest {
    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(TestConfig.class);


    @Test
    @DisplayName("부모 타입으로 조회시 자식타입이 둘 이상이면 중복 오류")
    void 자식타입중복(){
        //DiscountPolicy bean = ac.getBean(DiscountPolicy.class);
        assertThrows(NoUniqueBeanDefinitionException.class, ()-> ac.getBean(DiscountPolicy.class));
    }


    @Test
    @DisplayName("부모 타입으로 조회시 자식타입이 둘 이상이면 이름 지정하기")
    void 자식타입이름지정(){
        DiscountPolicy bean = ac.getBean("rateDiscountPolicy",DiscountPolicy.class);
        assertThat(bean).isInstanceOf(DiscountPolicy.class);      
    }

    @Test
    @DisplayName("특정하위타입으로 조회")
    void 특정하위타입조회(){
        DiscountPolicy bean = ac.getBean(RateDiscountPolicy.class); // 구현체에 의존하므로 좋지 않음
        assertThat(bean).isInstanceOf(RateDiscountPolicy.class);      
    }


    @Test
    @DisplayName("부모타입으로 모두 조회")
    void 부모타입으로조회(){
        Map<String, DiscountPolicy> beansOfType = ac.getBeansOfType(DiscountPolicy.class);
        assertThat(beansOfType.size()).isEqualTo(2);

        for (String key : beansOfType.keySet()) {
            System.out.println("key : " + key + "  value : "+beansOfType.get(key));
        }
    }

    @Test
    @DisplayName("부모타입으로 모두 조회-Object")
    void 오브젝트로조회(){
        Map<String, Object> beansOfType = ac.getBeansOfType(Object.class);  // 스프링 내부적으로 쓰는 bean까지 다 튀어나옴

        for (String key : beansOfType.keySet()) {
            System.out.println("key : " + key + "  value : "+beansOfType.get(key));
        }
    }

    @Configuration 
    static class TestConfig{

        @Bean
        public DiscountPolicy rateDiscountPolicy(){
            return new RateDiscountPolicy();
        }

        @Bean
        public DiscountPolicy fixDiscountPolicy(){
            return new FixDiscountPolicy();
        }
    }
}
