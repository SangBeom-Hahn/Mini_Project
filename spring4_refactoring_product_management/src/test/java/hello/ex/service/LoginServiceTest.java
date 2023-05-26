package hello.ex.service;

import hello.ex.domain.member.Member;
import hello.ex.repository.member.MemberRepository;
import hello.ex.repository.member.dto.MemberSaveDTO;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
@Slf4j
@SpringBootTest
class LoginServiceTest {
    @Autowired
    LoginService loginService;

    @Autowired
    MemberRepository memberRepository;


    @Test
    void loginSuccess() {
        MemberSaveDTO memberSaveDTO = new MemberSaveDTO();
        memberSaveDTO.setLoginId("a");
        memberSaveDTO.setPassword("b");
        memberSaveDTO.setUserName("hsb");

        Member saveMember = memberRepository.save(memberSaveDTO);

        Optional<Member> loginMember = loginService.login("a", "b");

        assertThat(loginMember.orElse(null).getLoginId()).isEqualTo("a");
    }

    @Test
    void loginFail() {
        MemberSaveDTO memberSaveDTO = new MemberSaveDTO();
        memberSaveDTO.setLoginId("a");
        memberSaveDTO.setPassword("b");
        memberSaveDTO.setUserName("hsb");

        Member saveMember = memberRepository.save(memberSaveDTO);

        Optional<Member> loginMember = loginService.login("a", "c");

        Assertions.assertTrue(loginMember.isEmpty());
    }
}