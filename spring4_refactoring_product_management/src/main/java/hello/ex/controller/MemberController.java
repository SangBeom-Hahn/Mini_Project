package hello.ex.controller;

import hello.ex.domain.member.Member;
import hello.ex.repository.member.MemberRepository;
import hello.ex.repository.member.dto.MemberSaveDTO;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class MemberController {
    private final MemberRepository memberRepository;

    @GetMapping("/members/add")
    public String addForm(@ModelAttribute MemberSaveDTO memberSaveDTO) {
        return "members/addMemberForm";
    }

    @PostMapping("/members/add")
    public String add(@Validated @ModelAttribute MemberSaveDTO memberSaveDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "members/addMemberForm";
        }

        memberRepository.save(memberSaveDTO);
        return "redirect:/";
    }
}
