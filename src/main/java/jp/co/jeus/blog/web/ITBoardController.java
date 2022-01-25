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
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

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

    @GetMapping("register/form")
    public String register(BoardValidationForm form, Model model) {
        return "it-bulletin-board-register";
    }

    @RequestMapping(value = "register", method = RequestMethod.POST, consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public String register(@Validated BoardValidationForm form, BindingResult bindingResult, Model model) {
        if (bindingResult != null && bindingResult.hasErrors()) {
            List<String> errors = new ArrayList<>();
            for (FieldError err : bindingResult.getFieldErrors()) {
                errors.add(err.getRejectedValue() + err.getCode());
            }
            model.addAttribute("errors", errors);
            return "it-bulletin-board-register";
        }
        Board board = boardService.saveBoard(Board.builder()
                .boardName(form.getBoardNameInput())
                .category(form.getCategoryInput())
                .color(form.getColorSelect().replace("bg", "text"))
                .description(form.getDetailsArea())
                .build());
        return "redirect:/it/board/main/bulletin";
    }

}
