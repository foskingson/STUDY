package foskingson.jdbc.repository;

import static foskingson.jdbc.connection.ConnectionConst.PASSWORD;
import static foskingson.jdbc.connection.ConnectionConst.URL;
import static foskingson.jdbc.connection.ConnectionConst.USERNAME;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.sql.SQLException;
import java.util.NoSuchElementException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import com.zaxxer.hikari.HikariDataSource;

import foskingson.jdbc.domain.Member;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MemberRepositoryV1Test {
    MemberRepositoryV1 repository;

    @BeforeEach
    void beforeEach(){
        // 기본 DriverManager - 항상 새로운 커넥션
        // DriverManagerDataSource dataSource = new DriverManagerDataSource(URL,USERNAME,PASSWORD);


        // 커넥션 풀링
        HikariDataSource dataSource = new HikariDataSource();
        dataSource.setJdbcUrl(URL);
        dataSource.setUsername(USERNAME);
        dataSource.setPoolName(PASSWORD);

        repository = new MemberRepositoryV1(dataSource);
    }

    @Test
    void crud() throws SQLException, InterruptedException {
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

        Thread.sleep(1000);
    }

    @Test
    void delete() throws SQLException{
        repository.delete("memberV2");
    }
}
