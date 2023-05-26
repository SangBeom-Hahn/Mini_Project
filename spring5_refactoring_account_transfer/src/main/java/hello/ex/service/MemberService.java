package hello.ex.service;

import hello.ex.domain.Log;
import hello.ex.domain.Member;
import hello.ex.exception.NoSuchIdPlzReTryException;
import hello.ex.exception.NotEnoughMoneyException;
import hello.ex.repository.log.LogRepository;
import hello.ex.repository.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionSynchronizationManager;

import java.util.Random;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;
    private final LogRepository logRepository;
    public void accountTransfer(String fromId, String toId, int money) throws NotEnoughMoneyException, NoSuchIdPlzReTryException {
        bizlogic(fromId, toId, money);
    }

    private void bizlogic(String fromId, String toId, int money) throws NotEnoughMoneyException, NoSuchIdPlzReTryException {
        Member fromMember = memberRepository.findByLoginId(fromId);
        Member toMember = memberRepository.findByLoginId(toId);

        log.info("트랜잭션 작동확인 = {}", TransactionSynchronizationManager.isActualTransactionActive());

        int fromMoney = fromMember.getMoney() - money;

        if (fromMoney <= 0) {
            throw new NotEnoughMoneyException("잔고 부족");
        }

        memberRepository.update(fromId, fromMoney);
        memberRepository.update(toId, toMember.getMoney() + money);
        throw new RuntimeException("시스템 예외");
    }

    public void joinV1(String username) {
        Member a = new Member("", "a", username, 10000);
        Log logMessage = new Log(username);

        log.info("== memberRepository 호출 시작 ==");
        memberRepository.save(a);
        log.info("== memberRepository 호출 종료 ==");

        log.info("== logRepository 호출 시작 ==");
        logRepository.save(logMessage);
        log.info("== logRepository 호출 종료 ==");
    }

    public void joinV2(String username) {
        Member a = new Member("", "a", username, 10000);
        Log logMessage = new Log(username);

        log.info("== memberRepository 호출 시작 ==");
        memberRepository.save(a);
        log.info("== memberRepository 호출 종료 ==");

        log.info("== logRepository 호출 시작 ==");
        try {
            logRepository.save(logMessage);
        } catch (RuntimeException e) {
            log.info("log 저장에 실패 = {}", logMessage.getMessage());
            log.info("정상 흐름 반환");
        }
        log.info("== logRepository 호출 종료 ==");
    }

//    public Member join(Member member) {
//        boolean active = TransactionSynchronizationManager.isSynchronizationActive();
//        log.info("트랜잭션 엑티브 = {}", active);
//        try {
//            memberRepository.save(member);
//            return member;
//        } catch (DuplicateKeyException e) {
//            String retryId = generateNewId(member.getLoginId());
//            member.setLoginId(retryId);
//            memberRepository.save(member);
//            return member;
//        }
//    }

    public Member join(Member member) {
        memberRepository.save(member);
        return member;
    }

    private String generateNewId(String loginId) {
        return loginId + new Random().nextInt(10);
    }
}
