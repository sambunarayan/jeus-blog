package jp.co.jeus.blog.web;

import jp.co.jeus.blog.service.ITBulletinBoardService;
import jp.co.jeus.blog.web.dto.PostResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RequiredArgsConstructor
@Controller
public class ITBulletinK8sBoardController {

    @Autowired
    private ITBulletinBoardService service;

    @GetMapping("/it/board/k8s")
    public String board(Model model) {
        model.addAttribute("posts", service.findAllDesc());
        return "it-bulletin-kubernetes";
    }
}
