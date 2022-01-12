package jp.co.jeus.blog.web;

import jp.co.jeus.blog.domain.posts.Board;
import jp.co.jeus.blog.domain.posts.Post;
import jp.co.jeus.blog.service.ITBulletinBoardService;
import jp.co.jeus.blog.service.ITBulletinPostService;
import jp.co.jeus.blog.web.dto.CurrentPostResponseDto;
import jp.co.jeus.blog.web.dto.PostResponseDto;
import jp.co.jeus.blog.web.dto.PostSaveRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@RequiredArgsConstructor
@RequestMapping("/it/board")
@Controller
public class ITBulletinBoardController {

    @Autowired
    private ITBulletinPostService postService;
    @Autowired
    private ITBulletinBoardService boardService;

    @GetMapping("bulletin")
    public String board(Model model) {
        model.addAttribute("boards", boardService.findAll());
        return "it-bulletin";
    }

    @GetMapping("register")
    public String register() {
        return "it-bulletin-board-register";
    }

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

    @GetMapping("posting/{board}")
    public String posting(@PathVariable String board, @RequestParam(name = "page", required = false) Long page, Model model) {
        model.addAttribute("board_name", board);
        model.addAttribute("current_page", page);
        return "it-bulletin-posting";
    }

    @PostMapping("posting/{board}")
    public String posting(@PathVariable String board, @RequestParam HashMap<String, String> formData, Model model) {
        model.addAttribute("board_name", board);
        model.addAttribute("post", postService.findById(Long.parseLong(formData.get("boardId"))));
        model.addAttribute("current_page", formData.get("hidden_current_page"));
        return "it-bulletin-posting";
    }

    @RequestMapping(value = "register", method = RequestMethod.POST, consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public String register(@RequestParam HashMap<String, String> formData, Model model) {
        Board board = boardService.saveBoard(Board.builder()
                .boardName(formData.get("boardNameInput"))
                .category(formData.get("categoryInput"))
                .color(formData.get("colorSelect"))
                .description(formData.get("detailsArea"))
                .build());
        return "redirect:/it/board/bulletin";
    }

    @RequestMapping(value = "posting", method = RequestMethod.POST, consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public String posting(@RequestParam HashMap<String, String> formData, Model model) {
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
        return "redirect:/it/board/list/" + formData.get("boardName") + "?bno=" + post.getId() + currPage;
    }
}
