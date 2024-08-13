package foskingson.jpabook.repository;

import org.springframework.stereotype.Repository;
import java.util.List;

import foskingson.jpabook.domain.Member;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;

@Repository
@AllArgsConstructor
public class MemberRepository {
    
    private final EntityManager em;

    public Long save(Member member){
        em.persist(member);
        return member.getId();
    }

    public Member findOne(Long id){
        return em.find(Member.class,id);
    }

    public List<Member> findAll(){
        return em.createQuery("select m from Member m", Member.class)
            .getResultList();
    }

    public List<Member> findByName(String name){
        return em.createQuery("select m from Member m where m.name= :name ", Member.class)
            .setParameter("name", name)
            .getResultList();
    }
}
