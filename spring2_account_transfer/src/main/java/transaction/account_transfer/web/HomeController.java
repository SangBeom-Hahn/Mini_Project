package transaction.account_transfer.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.SessionAttribute;
import transaction.account_transfer.domain.member.Member;

@Controller
public class HomeController {
    @GetMapping("/")
    public String home(@SessionAttribute(name = SessionConst.SESSION_NAME, required = false) Member member, Model model) {
        if (member == null) {
            return "home";
        }

        model.addAttribute("member", member);
        return "loginHome";
    }
}
