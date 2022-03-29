package jp.co.jeus.blog.web;

import jp.co.jeus.blog.service.ImageUploadService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Log4j2
@RequestMapping("RestImage")
@RestController
public class ImageRestController {

    @Autowired
    private ImageUploadService imageUploadService;

    @RequestMapping(value="/upload", method= RequestMethod.POST, produces="application/json")
    @ResponseBody
    public String uploadImage(MultipartFile file) {
        log.debug(file.getOriginalFilename());
        return imageUploadService.uploadLogoImage(file);
    }
}
