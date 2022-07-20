package jp.co.jeus.blog.web;

import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * Login Test page
 */
@Log4j2
@Controller
public class LoginController {

    /**
     *
     * @return
     */
    @PostMapping("login")
    public String login() {
        log.debug("Login");
        return "/";
    }
}
