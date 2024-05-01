package foskingson.jdbc.repository;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import javax.sql.DataSource;

import org.springframework.jdbc.support.JdbcUtils;

import foskingson.jdbc.connection.DBConnectionUtil;
import foskingson.jdbc.domain.Member;
import lombok.extern.slf4j.Slf4j;

// JDBC - 데이터소스 사용, JdbcUtils 사용
@Slf4j
public class MemberRepositoryV1 {

    private final DataSource dataSource;

    public MemberRepositoryV1(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public Member save(Member member) throws SQLException{
        String sql = "insert into member(member_id,money) values (?,?)";
        Connection con =null;
        PreparedStatement pstmt =null;

        
        try {
            con=getConnection();
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, member.getMemberId());
            pstmt.setInt(2, member.getMoney());
            pstmt.executeUpdate();
            return member;
        } catch (SQLException e) {
            log.error("db error", e);
            throw e;
        }finally{
            close(con,pstmt, null);
        }
    }

    public Member findById(String memberId) throws SQLException{
        String sql ="select * from member where member_id=?";
        Connection con =null;
        PreparedStatement pstmt =null;
        ResultSet rs =null;
        try {
            con=getConnection();
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, memberId);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                Member member = new Member();
                member.setMemberId(rs.getString("member_id"));
                member.setMoney(rs.getInt("money"));
                return member;
            }else{
                throw new NoSuchElementException("member not found memberId="+memberId);
            }
        } catch (SQLException e) {
            log.error("db error", e);
            throw e;
        }finally{
            close(con, pstmt, rs);
        }
    }

    public List<Member> findAll(){
        String sql ="select * from member";
        Connection con =null;
        PreparedStatement pstmt =null;
        ResultSet rs =null;

        List<Member> members = new ArrayList<>();

        try {
            // Connection 객체 생성
            con = getConnection();
            
            // PreparedStatement 객체 생성
            pstmt = con.prepareStatement(sql);
            
            // 쿼리 실행
            rs = pstmt.executeQuery();
            
            // 결과 처리
            while (rs.next()) {
                // 결과에서 각 열의 값을 가져와서 Member 객체 생성
                Member member = new Member();
                member.setMemberId(rs.getString("member_id"));
                member.setMoney(rs.getInt("money"));
                // 필요한 다른 속성들도 가져와서 설정
                
                // Member 객체를 리스트에 추가
                members.add(member);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // 리소스 해제
            try {
                if (rs != null) rs.close();
                if (pstmt != null) pstmt.close();
                if (con != null) con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        
        // 결과 리스트 반환
        return members;
        
    }

    public void update(String memberId, int money) throws SQLException{
        String sql = "update member set money=? where member_id=?";
        Connection con =null;
        PreparedStatement pstmt =null;
        ResultSet rs =null;

        try {
            con=getConnection();
            pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, money);
            pstmt.setString(2,memberId);
            int resultSize = pstmt.executeUpdate();
            log.info("resultSize={}", resultSize);
        } catch (SQLException e) {
            log.error("db error", e);
            throw e;
        }finally{
            close(con,pstmt, null);
        }
    }

    public void delete(String memberId) throws SQLException{
        String sql = "delete from member where member_id=?";
        Connection con =null;
        PreparedStatement pstmt =null;
        ResultSet rs =null;

        try {
            con=getConnection();
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1,memberId);
            int resultSize = pstmt.executeUpdate();
        } catch (SQLException e) {
            log.error("db error", e);
            throw e;
        }finally{
            close(con,pstmt, null);
        }
    }

    private void close(Connection con,  Statement stmt, ResultSet rs){
        JdbcUtils.closeResultSet(rs);
        JdbcUtils.closeStatement(stmt);
        JdbcUtils.closeConnection(con);
    }

    private Connection getConnection() throws SQLException {
        Connection con = dataSource.getConnection();
        log.info("get connection={}, class={}", con, con.getClass());
        return con;
    }
}
