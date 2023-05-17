package transaction.account_transfer.web.login;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import transaction.account_transfer.domain.member.Member;
import transaction.account_transfer.service.LoginService;
import transaction.account_transfer.web.SessionConst;
import transaction.account_transfer.web.login.form.LoginForm;

import static transaction.account_transfer.web.SessionConst.SESSION_NAME;

@Slf4j
@Controller
@RequiredArgsConstructor
public class LoginController {
    private final LoginService loginService;

    @GetMapping("/login")
    public String loginForm(@ModelAttribute("loginForm") LoginForm form) {
        return "login/loginForm";
    }

    @PostMapping("/login")
    public String login(@Validated @ModelAttribute("loginForm") LoginForm form, BindingResult bindingResult,
                        HttpServletRequest request, @RequestParam(defaultValue = "/") String redirectURI) {
        if (bindingResult.hasErrors()) {
            log.info("login: binding result error = {}", bindingResult);
            return "login/loginForm";
        }

        Member loginMember = loginService.login(form.getLoginId(), form.getPassword());
        if (loginMember == null) {
            bindingResult.reject("loginFail", "loginFail");
        }

        HttpSession session = request.getSession();
        session.setAttribute(SESSION_NAME, loginMember);
        return "redirect:" + redirectURI;
    }

    @PostMapping("/logout")
    public String logout(HttpServletRequest request) {
        HttpSession session = request.getSession();
        Member member = (Member) session.getAttribute(SESSION_NAME);

        if (member != null) {
            session.invalidate();
        }
        return "redirect:/";
    }
}