package jp.co.jeus.blog.web;

import jp.co.jeus.blog.service.ImageUploadService;
import jp.co.jeus.blog.web.dto.ImageResponseDto;
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
    public ImageResponseDto uploadImage(MultipartFile file) {
        log.debug(file.getOriginalFilename());
        return new ImageResponseDto(imageUploadService.uploadLogoImage(file));
    }
}
