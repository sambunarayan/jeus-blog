package jp.co.jeus.blog.web;

import jp.co.jeus.blog.domain.posts.Board;
import jp.co.jeus.blog.form.BoardValidationForm;
import jp.co.jeus.blog.service.ITBulletinBoardService;
import jp.co.jeus.blog.validate.BoardValidator;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@Log4j2
@RequestMapping("/it/board/main")
@Controller
public class ITBoardController {

    @Autowired
    private BoardValidator boardValidator;
    @Autowired
    private ITBulletinBoardService boardService;

    @InitBinder
    public void validatorBinder(WebDataBinder binder) {
        binder.addValidators(boardValidator);
    }

    @GetMapping("bulletin")
    public String board(Model model) {
        log.debug("X-Tracking-Id --> " + model.getAttribute("trackingId"));
        model.addAttribute("boards", boardService.findAll());
        return "it-bulletin";
    }

    @GetMapping("register")
    public String register(BoardValidationForm form, Model model) {
        model.addAttribute("board",new BoardValidationForm());
        return "it-bulletin-board-register";
    }

    @RequestMapping(value = "register", method = RequestMethod.POST, consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public String register(@Validated BoardValidationForm form, BindingResult bindingResult, @RequestParam HashMap<String, String> formData, Model model) {
        if (bindingResult.hasErrors()) {
            return "/it/board/main/register";
        }
        Board board = boardService.saveBoard(Board.builder()
                .boardName(formData.get("boardNameInput"))
                .category(formData.get("categoryInput"))
                .color(formData.get("colorSelect"))
                .description(formData.get("detailsArea"))
                .build());
        return "redirect:/it/board/main/bulletin";
    }

}
