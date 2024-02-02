package hello.hellospring.repository;

import java.util.List;
import java.util.Optional;

import hello.hellospring.domain.Member;
import jakarta.persistence.EntityManager;

// JPA와 스프링데이터JPA는 다른기술이니 주의할것

public class JpaMemberRepository implements memberRepository {

    private final EntityManager em;

    public JpaMemberRepository(EntityManager em){
        this.em = em;
    }

    @Override
    public List<Member> findAll() {
        List<Member> result = em.createQuery("select m from Member m",Member.class)
            .getResultList();
        return result;

    }

    @Override
    public Optional<Member> findById(Long id) {
       Member member = em.find(Member.class, id);   //pk기반은 find로 찾을수 있지만 나머지는 createQuery를 통해 찾아야 한다.
       return Optional.ofNullable(member);
    }

    @Override
    public Optional<Member> findByName(String name) {
       List<Member> result = em.createQuery("select m from Member m where m.name=:name",Member.class)
            .setParameter("name", name)
            .getResultList();
       return result.stream().findAny();
    }

    @Override
    public Member save(Member member) {
        em.persist(member);
        return member;
    }
    
}
