package transaction.account_transfer.domain.member.repository.jdbctemplate;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import transaction.account_transfer.domain.member.Member;
import transaction.account_transfer.repository.member.MemberRepository;

import static org.junit.jupiter.api.Assertions.*;
@Slf4j
@Transactional
@SpringBootTest
class JdbcTemplateMemberRepositoryTest {
    @Autowired
    private MemberRepository memberRepository;

    @Test
    void saveEx() {
        Member member = new Member("q", "h", "h", 10);
        memberRepository.save(member);
    }
}