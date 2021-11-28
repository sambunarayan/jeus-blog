package jp.co.jeus.blog.web;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@RequiredArgsConstructor
@Controller
public class ITBulletinK8sBoardController {

    @GetMapping("/it/board/k8s")
    public String board() {
        return "it-bulletin-kubernetes";
    }
}
