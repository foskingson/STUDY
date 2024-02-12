package hello.hellospring.repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import hello.hellospring.domain.Member;

// 전구를 눌러 자동으로 오버라이딩 가능
//@Repository 
public class MemoryMemberRepository implements memberRepository {
    private static Map<Long,Member> store =new HashMap<>(); //해쉬맵은 키와값으로 이루어진 표
    private static long sequence =0L;


    @Override
    public List<Member> findAll() {
        // TODO Auto-generated method stub
        return new ArrayList<>(store.values());
    }

    @Override
    public Optional<Member> findById(Long id) {
        // TODO Auto-generated method stub
        return Optional.ofNullable(store.get(id));    // 해쉬맵에 get()을 통해 해당 id에 대한 값 즉 이름을 얻을 수 있다.
        // Null 이 나올 가능성이 있다면 Optional.ofNullable()로 감싸기
    }

    @Override
    public Optional<Member> findByName(String name) {
        // TODO Auto-generated method stub
        return store.values().stream()
            .filter(member->member.getName().equals(name)) // 주어진 조건자를 기반으로 요소를 필터링합니다. 이 경우 'name' 속성이 제공된 'name'과 일치하는 'Member' 개체를 필터링한다
            .findAny(); // 필터 조건자를 충족하는 스트림의 모든 요소를 ​​포함하는 Optional을 반환
    }

    @Override
    public Member save(Member member) {
        // TODO Auto-generated method stub
        member.setId(++sequence);
        store.put(member.getId(),member);
        return member;
    }
    
    public void clearStore(){
        store.clear();
    }
}
