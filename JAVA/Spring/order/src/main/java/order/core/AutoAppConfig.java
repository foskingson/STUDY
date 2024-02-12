package order.core;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.annotation.ComponentScan.Filter;

import order.core.member.MemberRepository;
import order.core.member.MemoryMemberRepository;

@Configuration
@ComponentScan(
    basePackages =  "order.core",
    excludeFilters=@Filter(type = FilterType.ANNOTATION, classes = Configuration.class)
    
)
public class AutoAppConfig {
    /*
    @Bean(name ="memoryMemberRepository")
    MemberRepository memberRepository(){
        return new MemoryMemberRepository();
    }
     */
}
