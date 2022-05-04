package jp.co.jeus.blog.web;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequiredArgsConstructor
@RequestMapping("accounts")
@Controller
public class AccountController {

    @PostMapping("login")
    public String login() {
        return "login";
    }
}
