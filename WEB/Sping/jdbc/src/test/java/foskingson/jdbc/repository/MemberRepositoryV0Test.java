package foskingson.jdbc.repository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.sql.SQLException;
import java.util.NoSuchElementException;

import org.junit.jupiter.api.Test;

import foskingson.jdbc.domain.Member;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MemberRepositoryV0Test {
    MemberRepositoryV0 repository = new MemberRepositoryV0();

    @Test
    void crud() throws SQLException {
        Member member = new Member("memberV100", 10000);
        repository.save(member);

        Member findMember = repository.findById(member.getMemberId());
        log.info("findmember={}",findMember);
        assertThat(findMember).isEqualTo(member);

        repository.update(member.getMemberId(), 30000);
        Member updateMember = repository.findById(member.getMemberId());
        assertThat(updateMember).isNotEqualTo(member);

        repository.delete(member.getMemberId());
        assertThatThrownBy(() -> repository.findById(member.getMemberId()))
            .isInstanceOf(NoSuchElementException.class);
    }

    @Test
    void delete() throws SQLException{
        repository.delete("memberV2");
    }
}
