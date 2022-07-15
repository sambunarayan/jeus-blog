package jp.co.jeus.blog.web;

import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Controller for JSP Test
 */
@Log4j2
@Controller
public class JspTestController {

    @GetMapping("test")
    public String test() {
        log.info("JspTestController in");
        return "test";
    }
}
