package transaction.account_transfer.repository.member.jdbctemplate;

import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import transaction.account_transfer.domain.member.Member;
import transaction.account_transfer.repository.member.MemberRepository;

import javax.sql.DataSource;
import java.util.List;
import java.util.Map;

@Slf4j
@Repository
public class JdbcTemplateMemberRepository implements MemberRepository {
    private final NamedParameterJdbcTemplate template;
    private final DataSource dataSource;

    public JdbcTemplateMemberRepository(DataSource dataSource) {
        this.dataSource = dataSource;
        this.template = new NamedParameterJdbcTemplate(dataSource);
    }

    @Override
    public Member save(Member member) {
        String sql = "insert into member(LOGINID, PASSWORD, USERNAME, MONEY) values(:loginId, :password, :username, :money)";

        KeyHolder keyHolder = new GeneratedKeyHolder();
        SqlParameterSource param = new BeanPropertySqlParameterSource(member);
        template.update(sql, param, keyHolder);
        log.info("save");
        return member;
    }

    @Override
    public List<Member> findAll() {
        String sql = "select LOGINID, PASSWORD, USERNAME, MONEY from member";
        log.info("findAll");
        return template.query(sql, memberRowMapper());
    }

    @Override
    public Member findByLoginId(String loginId) {
        String sql = "select LOGINID, PASSWORD, USERNAME, MONEY from member where LOGINID = :loginId";
        Map<String, Object> param = Map.of("loginId", loginId);
        log.info("find By Id");
        return template.queryForObject(sql, param, memberRowMapper());
    }

    @Override
    public void update(String id, int money) {
        String sql = "update member set MONEY = :money where LOGINID = :loginId";
        SqlParameterSource param = new MapSqlParameterSource()
                .addValue("loginId", id)
                .addValue("money", money);
        log.info("update");
        template.update(sql, param);
    }

    @Override
    public void delete(String id) {
        String sql = "delete from member where LOGINID = :loginId";
        Map<String, Object> param = Map.of("loginId", id);
        log.info("delete");
        template.update(sql, param);
    }

    private RowMapper<Member> memberRowMapper() {
        return BeanPropertyRowMapper.newInstance(Member.class);
    }
}
