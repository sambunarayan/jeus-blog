package jp.co.jeus.blog.web;

import jp.co.jeus.blog.domain.posts.Post;
import jp.co.jeus.blog.service.ITBulletinPostService;
import jp.co.jeus.blog.web.dto.CurrentPostResponseDto;
import jp.co.jeus.blog.web.dto.PostResponseDto;
import jp.co.jeus.blog.web.dto.PostSaveRequestDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.util.HashMap;

/**
 * IT Bulletin controller
 */
@Log4j2
@RequiredArgsConstructor
@RequestMapping("/it/board/post")
@Controller
public class ITBulletinPostController {

    @Autowired
    private ITBulletinPostService postService;

    /**
     * Get board by name
     *
     * @param boardName
     * @param bno
     * @param page
     * @param model
     * @return
     */
    @GetMapping("/list/{boardName}")
    public String board(@PathVariable String boardName, @RequestParam(name = "bno", required = false) Long bno,
                        @RequestParam(name = "page", required = false) Long page, Model model) {
        model.addAttribute("board_name", boardName);
        model.addAttribute("posts", postService.findByBoardNameDesc(boardName));
        if (bno != null) {
            PostResponseDto resDto = postService.findById(bno);
            if (resDto != null) {
                model.addAttribute("current_post", new CurrentPostResponseDto(resDto));
            }
        }
        model.addAttribute("current_page", page == null ? 1 : page);
        return "it-bulletin-board";
    }

    /**
     * Get positing page
     *
     * @param board
     * @param page
     * @param model
     * @return
     */
    @GetMapping("posting/{board}")
    public String posting(@PathVariable String board, @RequestParam(name = "page", required = false) Long page, Model model) {
        model.addAttribute("board_name", board);
        model.addAttribute("current_page", page);
        return "it-bulletin-posting";
    }

    /**
     * Get an existing posting page
     *
     * @param board
     * @param formData
     * @param model
     * @return
     */
    @PostMapping("posting/{board}")
    public String posting(@PathVariable String board, @RequestParam HashMap<String, String> formData, Model model) {
        model.addAttribute("board_name", board);
        model.addAttribute("post", postService.findById(Long.parseLong(formData.get("boardId"))));
        model.addAttribute("current_page", formData.get("hidden_current_page"));
        return "it-bulletin-posting";
    }

    /**
     * Put new posting
     *
     * @param formData
     * @param model
     * @param uploadFile
     * @return
     */
    @Transactional
    @RequestMapping(value = "posting", method = RequestMethod.POST)
    public String posting(@RequestParam HashMap<String, String> formData, Model model, MultipartFile[] uploadFile) {
        for (MultipartFile file : uploadFile) {
            log.info("File name ---> " + file.getOriginalFilename());
        }
        PostResponseDto post = null;
        String currPage = "&page=";
        if (formData.containsKey("id")) {
            currPage += formData.get("current_page");
            post = postService.update(PostSaveRequestDto.builder()
                    .id(Long.parseLong(formData.get("id")))
                    .title(formData.get("title"))
                    .content(formData.get("content"))
                    .build());
        } else {
            currPage += "1";
            post = postService.savePost(Post.builder()
                    .boardName(formData.get("boardName"))
                    .title(formData.get("title"))
                    .content(formData.get("content"))
                    .author("Guest")
                    .build());
        }
        return "redirect:/it/board/post/list/" + formData.get("boardName") + "?bno=" + post.getId() + currPage;
    }
}
