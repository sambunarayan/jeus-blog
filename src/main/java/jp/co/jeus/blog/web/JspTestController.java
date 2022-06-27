package jp.co.jeus.blog.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class JspTestController {

    @GetMapping("test")
    public String test() {
        return "/WEB-INF/views/test";
    }
}
