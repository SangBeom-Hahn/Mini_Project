package transaction.account_transfer.web.member.controller;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import transaction.account_transfer.domain.member.Member;
import transaction.account_transfer.domain.member.repository.MemberRepository;
import transaction.account_transfer.domain.member.service.MemberService;
import transaction.account_transfer.web.member.controller.form.AccountForm;
import transaction.account_transfer.web.member.controller.form.MemberAddForm;

import java.util.List;

@Controller
@Slf4j
@RequiredArgsConstructor
public class MemberController {
    private final MemberRepository memberRepository;
    private final MemberService memberService;

    @GetMapping("/members/add")
    public String addForm(@ModelAttribute("member") MemberAddForm form) {
        return "members/addMemberForm";
    }

    @PostMapping("/members/add")
    public String add(@Validated @ModelAttribute("member") MemberAddForm form, BindingResult bindingResult) {
        // 회원가입은 글로벌 오류가 필요없음
        if (bindingResult.hasErrors()) {
            log.info("binding result err = {}", bindingResult);
            return "members/addMemberForm";
        }

        Member member = new Member(form.getLoginId(), form.getPassword(), form.getUsername(), form.getMoney());
        memberRepository.save(member);
        return "redirect:/";
    }

    @GetMapping("/members")
    public String members(Model model) {
        List<Member> members = memberRepository.findAll();
        model.addAttribute("members", members);
        return "members/members";
    }

    @GetMapping("/members/{loginId}")
    public String member(@PathVariable String loginId, Model model) {
        Member findMember = memberRepository.findByLoginId(loginId);
        model.addAttribute("member", findMember);
        return "members/member";
    }

    @GetMapping("/members/accountTransfer")
    public String accountForm(@ModelAttribute("account") AccountForm form) {
        return "members/accountForm";
    }

    @PostMapping("/members/accountTransfer")
    public String account(@ModelAttribute("account") AccountForm form) {
        memberService.accountTransfer(form.getFromId(), form.getToId(), form.getMoney());
        return "redirect:/members";
    }
}
