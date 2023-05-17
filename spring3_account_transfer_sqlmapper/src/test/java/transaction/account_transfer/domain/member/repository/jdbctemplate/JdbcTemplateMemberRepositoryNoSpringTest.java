package transaction.account_transfer.domain.member.repository.jdbctemplate;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.transaction.annotation.Transactional;
import transaction.account_transfer.domain.member.Member;
import transaction.account_transfer.repository.member.MemberRepository;
import transaction.account_transfer.repository.member.jdbctemplate.JdbcTemplateMemberRepository;

@Slf4j
@Transactional
class JdbcTemplateMemberRepositoryNoSpringTest {
    private MemberRepository memberRepository;
    @BeforeEach
    void beforeEach() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource("jdbc:h2:tcp://localhost/~/test", "sa", "");
        this.memberRepository = new JdbcTemplateMemberRepository(dataSource);
    }

    @Test
    void saveEx() {
        Member member = new Member("q", "h", "h", 10);
        memberRepository.save(member);
    }
}