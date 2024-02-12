package hello.hellospring.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import hello.hellospring.domain.Member;

public interface SpringDataJpaMemberRepository  extends JpaRepository<Member, Long>, memberRepository { // interface가 interface한테 내려받을때는 extends
    // 스프링데이터 jpa는 구현체를 알아서 만들어준다 

    @Override                                     
    Optional<Member> findByName(String name); // 이런식으로 코드를 짜놓으면 스프링데이터JPA가 select m from Member m where m.name=? 이런식으로 jql문을 짜준다
    
}
