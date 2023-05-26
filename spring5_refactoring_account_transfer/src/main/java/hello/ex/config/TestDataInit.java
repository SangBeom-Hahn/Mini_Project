package hello.ex.config;

import hello.ex.domain.Member;
import hello.ex.repository.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

@RequiredArgsConstructor
public class TestDataInit {
    private final MemberRepository memberRepository;

    @EventListener(ApplicationReadyEvent.class)
    public void initData() {
        memberRepository.save(new Member("a", "b", "c", 10000));
        memberRepository.save(new Member("1", "2", "3", 50000));
    }
}
