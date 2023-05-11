package hello.ex.web.member.controller;

import hello.ex.domain.member.Member;
import hello.ex.domain.member.repository.MemberRepository;
import hello.ex.web.member.form.MemberSaveForm;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.annotation.PostConstruct;
import java.util.NoSuchElementException;

@Slf4j
@Controller
@RequiredArgsConstructor
public class MemberController {
    private final MemberRepository memberRepository;

    @GetMapping("/members/add")
    public String addForm(@ModelAttribute("member") MemberSaveForm form) {
        return "members/addMemberForm";
    }

    @PostMapping("members/add")
    public String add(@Validated @ModelAttribute("member") MemberSaveForm form, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            log.info("{}", bindingResult);
            return "members/addMemberForm";
        }

        Member member = new Member(form.getUserName(), form.getLoginId(), form.getPassword());
        memberRepository.save(member);

        return "redirect:/";
    }
}
