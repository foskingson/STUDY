package hello.login.domain.member;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import jakarta.validation.constraints.Null;
import lombok.extern.slf4j.Slf4j;

@Repository
@Slf4j
public class MemberRepository {
    private static Map<Long,Member> store = new HashMap<>();
    private static long sequence = 0L;

    public Member save(Member member){
        member.setId(++sequence);
        log.info("save:member = {}",member);
        store.put(member.getId(), member);
        return member;
    }

    public Optional<Member> findByLoginId(String loginId){
        // List<Member> members = findAll();
        // for (Member member : members) {
        //     if (member.getLoginId().equals(loginId)) {
        //         return Optional.of(member);
        //     }
        // }
        // return Optional.empty();

        return findAll().stream()
            .filter(member->member.getLoginId().equals(loginId))
            .findFirst();
    }

    public Member findById(Long id){
        return store.get(id);
    }

    public List<Member> findAll(){
        return new ArrayList<>(store.values());
    }

    public void clearStore(){
        store.clear();
    }
}
