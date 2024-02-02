package hello.hellospring.repository;

import java.util.Optional;

import hello.hellospring.domain.Member;
import java.util.List;

public interface memberRepository {
    Member save(Member member);
    Optional<Member> findById(Long id); 
    Optional<Member> findByName(String name);
    List<Member> findAll();

}
 