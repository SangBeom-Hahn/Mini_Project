package hello.ex.web;

import hello.ex.domain.member.Member;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.SessionAttribute;

@Controller
public class HomeController {
    @GetMapping("/")
    public String home(@SessionAttribute(name = Const.MySessionName, required = false) Member member, Model model) {
        if(member == null) {
            return "home";
        }

        model.addAttribute("member", member);
        return "loginHome";
    }
}
