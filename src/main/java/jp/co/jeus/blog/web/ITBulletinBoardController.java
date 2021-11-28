package jp.co.jeus.blog.web;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@RequiredArgsConstructor
@Controller
public class ITBulletinBoardController {

    @GetMapping("/it/board")
    public String board() {
        return "it-bulletin";
    }
}
