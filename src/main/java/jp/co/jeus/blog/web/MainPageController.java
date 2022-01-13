package jp.co.jeus.blog.web;

import jp.co.jeus.blog.service.ITBulletinPostService;
import jp.co.jeus.blog.web.dto.PostResponseDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Log4j2
@RequiredArgsConstructor
@Controller
public class MainPageController {

    @Autowired
    private ITBulletinPostService service;

    @GetMapping
    public String index(Model model) {
//        List<PostResponseDto> posts = service.findLatestPost(-1L);
//        model.addAttribute("LatestPost", posts);
//        model.addAttribute("LastIndexId", posts.get(posts.size() - 1).getId());
        model.addAttribute("LastIndexId", Integer.MAX_VALUE);
        return "index";
    }
}