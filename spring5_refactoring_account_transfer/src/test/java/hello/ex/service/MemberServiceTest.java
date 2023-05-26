package hello.ex.service;

import hello.ex.domain.Log;
import hello.ex.domain.Member;
import hello.ex.exception.NoSuchIdPlzReTryException;
import hello.ex.exception.NotEnoughMoneyException;
import hello.ex.repository.log.LogRepository;
import hello.ex.repository.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.support.TransactionSynchronizationManager;

@Slf4j
@SpringBootTest
@RequiredArgsConstructor
class MemberServiceTest {
    @Autowired
    private MemberService memberService;

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private LogRepository logRepository;

    @Test
    void accountTransfer() throws NotEnoughMoneyException, NoSuchIdPlzReTryException {
        Member a = new Member("a", "a", "a", 10000);
        Member b = new Member("b","b","b", 10000);

        memberRepository.save(a);
        memberRepository.save(b);

        try {
            memberService.accountTransfer(a.getLoginId(), b.getLoginId(), 12000);
        } catch (NotEnoughMoneyException e) {
            log.info("잔고 부족입니다. 다른 계좌로 이어서 부탁드립니다.");
        }
    }

    @Test
    public void outerTxOff_success() {
        String username = "outerTxOff_success";
        memberService.joinV1(username);
    }

    @Test
    public void outerTxOff_fail() {
        String username = "로그예외outerTxOff_success";
        memberService.joinV1(username);
    }

    @Test
    public void outerTxOff_single() {
        String username = "outerTxOff_single";
        memberService.joinV1(username);
    }

    @Test
    public void outerTxOn_success() {
        String username = "outerTxOn_success";
        memberService.joinV1(username);
    }

    @Test
    public void outerTxOn_fail() {
        String username = "로그예외outerTxOn_fail";
        memberService.joinV1(username);
    }

    @Test
    public void recoverEx() {
        String username = "로그예외recoverEx";
        memberService.joinV2(username);
    }

    @Test
    public void join() {
        Member a = new Member("a", "a", "a", 10000);
        Member a2 = new Member("a", "a", "a", 10000);
        memberService.join(a);
        memberService.join(a2);
    }

    @Test
    public void findByLoginId() throws NoSuchIdPlzReTryException {
        Member a = new Member("a", "a", "a", 10000);
        memberService.join(a);

        memberRepository.findByLoginId("b");
    }
}