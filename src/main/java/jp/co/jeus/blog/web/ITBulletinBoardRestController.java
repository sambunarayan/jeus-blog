package jp.co.jeus.blog.web;

import jp.co.jeus.blog.service.ITBulletinPostService;
import jp.co.jeus.blog.web.dto.PostPageDto;
import jp.co.jeus.blog.web.dto.PostResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * IT Bulletin board rest controller
 */
@RequestMapping("/it/board/post")
@RequiredArgsConstructor
@RestController
public class ITBulletinBoardRestController {

    @Autowired
    private ITBulletinPostService service;

    @GetMapping("/latest/{id}")
    public List<PostResponseDto> getLatestPosts(@PathVariable("id") Long id) {
        return service.findLatestPost(id);
    }

    @GetMapping("/{boardName}/{id}")
    public PostResponseDto getK8sPostingList(@PathVariable("boardName") String boardName, @PathVariable("id") Long id) {
        return service.findById(id);
    }

    @GetMapping("/{boardName}/page/{page}")
    public PostPageDto getPostPage(@PathVariable("boardName") String boardName, @PathVariable("page") int page) {
        return service.findWithPaging(boardName.toLowerCase(), page);
    }

    /**
     * Delete posting
     *
     * @param id
     * @return
     */
    @Transactional
    @DeleteMapping("/delete/post/{id}")
    public Long deletePost(@PathVariable("id") Long id) {
        service.deletePost(id);
        return id;
    }
}
