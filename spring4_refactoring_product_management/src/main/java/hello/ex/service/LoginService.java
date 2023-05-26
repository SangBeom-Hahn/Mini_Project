package hello.ex.service;

import hello.ex.domain.member.Member;
import hello.ex.repository.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LoginService {
    private final MemberRepository memberRepository;

    public Optional<Member> login(String loginId, String password) {
        return Optional.of(memberRepository.findByLoginId(loginId)
                .filter(member -> member.getPassword().equals(password))
                .orElse(null));
    }
}
