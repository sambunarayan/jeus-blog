package jp.co.jeus.blog.web;

import jp.co.jeus.blog.service.ITBulletinPostService;
import jp.co.jeus.blog.web.dto.PostResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@RequiredArgsConstructor
@Controller
public class IndexController {

    @Autowired
    private ITBulletinPostService service;

    @GetMapping
    public String index(Model model) {
        model.addAttribute("LatestPost", service.findLatestPost());
        return "index";
    }
}
