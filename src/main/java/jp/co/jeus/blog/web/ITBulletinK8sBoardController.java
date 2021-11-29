package jp.co.jeus.blog.web;

import jp.co.jeus.blog.service.ITBulletinBoardService;
import jp.co.jeus.blog.web.dto.PostResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RequiredArgsConstructor
@Controller
public class ITBulletinK8sBoardController {

    @Autowired
    private ITBulletinBoardService service;

    @GetMapping("/it/board/k8s")
    public String board() {
        return "it-bulletin-kubernetes";
    }

    @GetMapping("/it/board/k8s/{id}")
    public PostResponseDto getK8sPostingList(@PathVariable Long id) {
        return service.findById(id);
    }
}
