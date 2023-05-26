package hello.ex.repository.member;

import hello.ex.domain.Member;
import hello.ex.exception.NoSuchIdPlzReTryException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.jdbc.support.JdbcUtils;
import org.springframework.jdbc.support.SQLErrorCodeSQLExceptionTranslator;
import org.springframework.jdbc.support.SQLExceptionTranslator;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

// DI 최적화 요구사항
@Slf4j
@Repository
@Transactional
public class MemberRepository {
    private final SQLExceptionTranslator translator;
    private final DataSource dataSource;


    public MemberRepository(DataSource dataSource) {
        this.dataSource = dataSource;
        this.translator = new SQLErrorCodeSQLExceptionTranslator(dataSource);
    }

    public Member save(Member member) {
        String sql = "insert into member(loginid, password, username, money) values(?, ?, ?, ?)";
        Connection con = null;
        PreparedStatement pstmt = null;

        try {
            con = getConnection();
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, member.getLoginId());
            pstmt.setString(2, member.getPassword());
            pstmt.setString(3, member.getUsername());
            pstmt.setInt(4, member.getMoney());
            pstmt.executeUpdate();
            return member;
        } catch (SQLException e) {
            throw translator.translate("insert", sql, e);
        } finally {
            close(con, pstmt, null);
        }
    }

    /**
     * 비즈니스 로직 예외 적용 전
     * @param loginId
     * @return
     */
    @Transactional(rollbackFor = NoSuchIdPlzReTryException.class)
    public Member findByLoginId(String loginId) throws NoSuchIdPlzReTryException {
        String sql = "select * from member where loginid = ?";

        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            con = getConnection();
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, loginId);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                Member member = new Member();
                member.setMoney(rs.getInt("money"));
                member.setLoginId(rs.getString("loginid"));
                member.setPassword(rs.getString("password"));
                member.setUsername(rs.getString("username"));
                return member;
            } else {
                throw new NoSuchIdPlzReTryException("그런 아이디 없습니다.");
            }
        } catch (SQLException e) {
            throw translator.translate("select", sql, e);
        } finally {
            close(con, pstmt, rs);
        }
    }

    public List<Member> findAll() {
        String sql = "select * from member";

        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            con = getConnection();
            pstmt = con.prepareStatement(sql);
            rs = pstmt.executeQuery();
            List<Member> store = new ArrayList<>();

            while (rs.next()) {
                Member member = new Member();
                member.setUsername(rs.getString("username"));
                member.setPassword(rs.getString("password"));
                member.setLoginId(rs.getString("loginid"));
                member.setMoney(rs.getInt("money"));
                store.add(member);
            }
            return store;
        } catch (SQLException e) {
            throw translator.translate("select all", sql, e);
        } finally {
            close(con, pstmt, rs);
        }
    }

    public void update(String loginId, int money) {
        String sql = "update member set money=? where loginId=?";

        Connection con = null;
        PreparedStatement pstmt = null;

        try {
            con = getConnection();
            pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, money);
            pstmt.setString(2, loginId);
            int resultSize = pstmt.executeUpdate();
            log.info("resultSize={}", resultSize);
        } catch (SQLException e) {
            log.info("{update member 에러 발생}", e);
            throw translator.translate("update", sql, e);
        } finally {
            close(con, pstmt, null);
        }
    }


    public void delete(String loginId) {
        String sql = "delete from member where loginId = ?";

        Connection con = null;
        PreparedStatement pstmt = null;

        try {
            con = getConnection();
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, loginId);
            int resultSize = pstmt.executeUpdate();
            log.info("resultSize={}", resultSize);
        } catch (SQLException e) {
            throw translator.translate("delete", sql, e);
        } finally {
            close(con, pstmt, null);
        }
    }

    public void close(Connection con, Statement stmt, ResultSet rs) {
        JdbcUtils.closeResultSet(rs);
        JdbcUtils.closeStatement(stmt);
        DataSourceUtils.releaseConnection(con, dataSource);
    }
    private Connection getConnection() {
        return DataSourceUtils.getConnection(dataSource);
    }
}
