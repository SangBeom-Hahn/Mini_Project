package transaction.account_transfer.repository.member.jdbc;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.jdbc.support.JdbcUtils;
import org.springframework.jdbc.support.SQLErrorCodeSQLExceptionTranslator;
import transaction.account_transfer.domain.member.Member;
import transaction.account_transfer.repository.member.MemberRepository;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Slf4j
//@Repository
@RequiredArgsConstructor //아래 처럼 해야지 주석처럼 하면 안된다.
public class JdbcMemberRepository implements MemberRepository {
//    private final DataSource dataSource; // 이거 주입 받아야함???
//    private final SQLExceptionTranslator translator = new SQLErrorCodeSQLExceptionTranslator(dataSource);

    private final DataSource dataSource;
    private final SQLErrorCodeSQLExceptionTranslator translator;
    @Autowired
    public JdbcMemberRepository(DataSource dataSource) {
        this.dataSource = dataSource;
        translator = new SQLErrorCodeSQLExceptionTranslator(dataSource);
    }

    @Override
    public Member save(Member member) {
        String sql = "insert into member(loginId, password, username, money) values(?, ?, ?, ?)";

        Connection con = null;
        PreparedStatement pstmt = null;

        try {
            // 여기 db 접속허는 로직 하는 중
            con = getConnection();
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, member.getLoginId());
            pstmt.setString(2, member.getPassword());
            pstmt.setString(3, member.getUsername());
            pstmt.setInt(4, member.getMoney());
            pstmt.executeUpdate();
            return member;

        } catch (SQLException e) {
            log.info("insert 도중 예외 발생", e);
            throw translator.translate("insert", sql, e);
        } finally {
            close(con, pstmt, null);
        }
    }

    /**
     * 조회를 했는데 없는 경우 예외 처리 해야함
     * @param loginId
     * @return
     */
    @Override
    public Member findByLoginId(String loginId) {
        String sql = "select * from member where loginId = ?";

        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            con = getConnection();
            pstmt =  con.prepareStatement(sql);
            pstmt.setString(1, loginId);

            rs = pstmt.executeQuery();
            if (rs.next()) {
                Member member = new Member();
                member.setUsername(rs.getString("username"));
                member.setMoney(rs.getInt("money"));
                member.setLoginId(rs.getString("loginId"));
                member.setPassword(rs.getString("password"));
                return member;
            } else {
                throw new NoSuchElementException("member not found memberId = " + loginId);
            }
        } catch (SQLException e) {
            throw translator.translate("select", sql, e);
        } finally {
            close(con, pstmt, rs);
        }
    }

    @Override
    public List<Member> findAll() {
        String sql = "select * from member";
        List<Member> members = new ArrayList<>();

        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            con = getConnection();
            pstmt = con.prepareStatement(sql);

            rs = pstmt.executeQuery();
            while (rs.next()) {
                Member member = new Member();
                member.setMoney(rs.getInt("money"));
                member.setPassword(rs.getString("password"));
                member.setLoginId(rs.getString("loginId"));
                member.setUsername(rs.getString("username"));

                members.add(member);
            }
            log.info("{}", members);
            return members;
        } catch (SQLException e) {
            throw translator.translate("select all", sql, e);
        }
    }

    @Override
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

    @Override
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