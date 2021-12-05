package jp.co.jeus.blog.web;

import jp.co.jeus.blog.service.ITBulletinBoardService;
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
    private ITBulletinBoardService service;

    @GetMapping("bulletin")
    public String board() {
        return "it-bulletin";
    }

    @GetMapping("k8s")
    public String board(Model model) {
        model.addAttribute("posts", service.findAllDesc());
        return "it-bulletin-kubernetes";
    }

    @GetMapping("posting/{board}")
    public String posting(@PathVariable String board, Model model) {
        model.addAttribute("board_name", board);
        return "it-bulletin-posting";
    }

    @RequestMapping(value="posting", method=RequestMethod.POST, consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public String posting(@RequestParam HashMap<String, String> formData, Model model) {
        System.out.println(formData);
        return "it-bulletin";
    }
}
