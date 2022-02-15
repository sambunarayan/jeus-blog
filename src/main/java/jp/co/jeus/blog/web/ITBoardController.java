package jp.co.jeus.blog.web;

import jp.co.jeus.blog.domain.posts.Board;
import jp.co.jeus.blog.form.BoardValidationForm;
import jp.co.jeus.blog.service.ITBulletinBoardService;
import jp.co.jeus.blog.service.ImageUploadService;
import jp.co.jeus.blog.validate.BoardValidator;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.stream.Collectors;

@Log4j2
@RequestMapping("/it/board/main")
@Controller
public class ITBoardController {

    @Autowired
    private BoardValidator boardValidator;
    @Autowired
    private ImageUploadService imageUploadService;
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

    @RequestMapping(value = "register", method = RequestMethod.POST)
    public String register(@RequestPart("logoFile") MultipartFile multipartFile, @Validated BoardValidationForm form, BindingResult bindingResult, Model model) {
        if (bindingResult != null && bindingResult.hasErrors()) {
            model.addAttribute("errors", bindingResult.getFieldErrors().stream()
                    .map(e -> e.getField() + e.getCode())
                    .collect(Collectors.toList()));
            return "it-bulletin-board-register";
        }
        String logoName = imageUploadService.uploadLogImage(multipartFile);
        Board board = boardService.saveBoard(Board.builder()
                .boardName(form.getBoardNameInput())
                .category(form.getCategoryInput())
                .color(form.getColorSelect().replace("bg", "text"))
                .logo(logoName)
                .description(form.getDetailsArea())
                .build());
        return "redirect:/it/board/main/bulletin";
    }

}
