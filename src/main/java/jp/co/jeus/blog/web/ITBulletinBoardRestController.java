package jp.co.jeus.blog.web;

import jp.co.jeus.blog.service.ITBulletinPostService;
import jp.co.jeus.blog.web.dto.PostResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class ITBulletinBoardRestController {

    @Autowired
    private ITBulletinPostService service;

    @GetMapping("/it/board/k8s/{id}")
    public PostResponseDto getK8sPostingList(@PathVariable Long id) {
        return service.findById(id);
    }
}
