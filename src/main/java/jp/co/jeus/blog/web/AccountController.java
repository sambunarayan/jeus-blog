package jp.co.jeus.blog.web;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Log4j2
@RequiredArgsConstructor
@RequestMapping("accounts")
@Controller
public class AccountController {

    @GetMapping("login")
    public String login() {
        return "login";
    }

    @GetMapping("login/proc")
    public String loginProcess() {
        log.debug("Login process");
        return "redirect:/";
    }
}
