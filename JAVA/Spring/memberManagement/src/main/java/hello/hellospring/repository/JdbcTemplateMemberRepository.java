package hello.hellospring.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.sql.DataSource;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import hello.hellospring.domain.Member;

public class JdbcTemplateMemberRepository implements memberRepository {
    private final JdbcTemplate jdbcTemplate;

    // @Autowired 생성자가 하나일땐 안해도됌
    public JdbcTemplateMemberRepository(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public List<Member> findAll() {
        return jdbcTemplate.query("select * from member", memberRowMapper());
    }

    @Override
    public Optional<Member> findById(Long id) {
        List<Member> result = jdbcTemplate.query("select * from member where id=?", memberRowMapper(),id);
        return result.stream().findAny();
    }

    @Override
    public Optional<Member> findByName(String name) {
        List<Member> result = jdbcTemplate.query("select * from member where name=?", memberRowMapper(),name);
        return result.stream().findAny();
    }

    @Override
    public Member save(Member member) {
        SimpleJdbcInsert jdbcInsert = new SimpleJdbcInsert(jdbcTemplate);
        jdbcInsert.withTableName("member").usingGeneratedKeyColumns("id");

        Map<String, Object> parameters = new HashMap<>();
        parameters.put("name", member.getName());

        Number key = jdbcInsert.executeAndReturnKey(new MapSqlParameterSource(parameters));
        member.setId(key.longValue());
        return member;
    }

    

    private RowMapper<Member> memberRowMapper() {
        return (ResultSet rs, int rowNum) -> { // 람다 표현식: RowMapper의 mapRow 메서드를 구현
            Member member = new Member(); // Member 객체 생성
            member.setId(rs.getLong("id")); // ResultSet에서 id를 가져와서 Member 객체에 설정
            member.setName(rs.getString("name")); // ResultSet에서 name을 가져와서 Member 객체에 설정
            return member;
        };
    }

}
