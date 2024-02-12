package hello.hellospring;
import hello.hellospring.aop.TimeTraceAop;
import hello.hellospring.repository.JdbcMemberRepository;
import hello.hellospring.repository.JdbcTemplateMemberRepository;
import hello.hellospring.repository.JpaMemberRepository;
import hello.hellospring.repository.JdbcMemberRepository;
import hello.hellospring.repository.memberRepository;
import hello.hellospring.repository.MemoryMemberRepository;
import hello.hellospring.service.MemberService;
import jakarta.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import javax.sql.DataSource;
import javax.swing.text.html.parser.Entity;
@Configuration
public class SpringConfig {
    private final memberRepository memberRepository;

    @Autowired
    public SpringConfig(memberRepository memberRepository){
        this.memberRepository=memberRepository;
    }

    @Bean
    public MemberService memberService() {
        return new MemberService(memberRepository);
    }   

    /*
    @Bean
    public memberRepository memberRepository() {
        // return new MemoryMemberRepository();
        //return new JpaMemberRepository(em);

    }
    */
}