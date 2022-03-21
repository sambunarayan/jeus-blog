package jp.co.jeus.blog.web;

import jp.co.jeus.blog.service.ITBulletinPostService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Log4j2
@PropertySource("classpath:message.property")
@RequiredArgsConstructor
@Controller
public class MainPageController {

    @Value("${main.title.message}")
    private String mainTitle;
    @Value("${main.title.detail.message}")
    private String mainTitleDetail;
    @Value("${profil.aboutme}")
    private String aboutMe;
    @Autowired
    private ITBulletinPostService service;

    /**
     * Returns the view name of the main page.
     *
     * @param model
     * @return view name
     */
    @GetMapping
    public String index(Model model) {
        model.addAttribute("LastIndexId", Integer.MAX_VALUE);
        model.addAttribute("aboutMe", aboutMe);
        model.addAttribute("mainTitle", mainTitle);
        model.addAttribute("mainTitleDetail", mainTitleDetail);
        return "index";
    }
}
