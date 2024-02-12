package order.core.beanfind;



import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Map;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoUniqueBeanDefinitionException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import order.core.AppConfig;
import order.core.member.MemberRepository;
import order.core.member.MemoryMemberRepository;

public class ApplicationContextSameBeanFindTest {
    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(sameBeanConfig.class);

    @Test
    @DisplayName(" 타입 조회시 같은 타입이 둘 이상이면 중복 오류 발생")
    void findByTypeDuplicate(){
        //MemberRepository bean = ac.getBean(MemberRepository.class);
        assertThrows(NoUniqueBeanDefinitionException.class, ()-> ac.getBean(MemberRepository.class));
    }
    
    @Test
    @DisplayName(" 타입 조회시 같은 타입이 둘 이상이면 빈 이름을 지정한다")
    void findByTypeName(){
        MemberRepository bean = ac.getBean("memberRepository1",MemberRepository.class);
        assertThat(bean).isInstanceOf(MemberRepository.class);
        
    }

    @Test
    @DisplayName(" 특정 타입 모두 조회")
    void findAllBeanByType(){
        Map<String,MemberRepository> beansOfType = ac.getBeansOfType(MemberRepository.class);
        for (String key : beansOfType.keySet()) {
            System.out.println("key : " + key + "value : " + beansOfType.get(key));
        }
        System.out.println(beansOfType);
        assertThat(beansOfType.size()).isEqualTo(2);
        

    }
 
    @Configuration
    static class sameBeanConfig{

        @Bean 
        public MemberRepository memberRepository1(){
            return new MemoryMemberRepository();
        }

        @Bean 
        public MemberRepository memberRepository2(){
            return new MemoryMemberRepository();
        }
    }
}
