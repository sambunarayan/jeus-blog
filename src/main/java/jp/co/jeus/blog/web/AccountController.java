package jp.co.jeus.blog.web;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * AccountsController
 */
@Log4j2
@RequiredArgsConstructor
@RequestMapping("accounts")
@Controller
public class AccountController {

    /**
     * Login
     *
     * @param error
     * @param logout
     * @param model
     * @return
     */
    @GetMapping("login")
    public String login(String error, String logout, Model model) {
        log.info("Custom login page");
        if (error != null) {
            log.error("login... error:{}", error);
            model.addAttribute("error", "Login Error! Check Your Account");
        }
        if (logout != null) {
            log.info("logout... {}", logout);
            model.addAttribute("logout", "Logout!");
        }
        return "login";
    }

    @PostMapping("login/proc")
    public String loginProcess() {
        log.debug("Login process");
        return "redirect:/";
    }
}
