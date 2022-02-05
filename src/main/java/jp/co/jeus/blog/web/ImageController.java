package jp.co.jeus.blog.web;

import jp.co.jeus.blog.properties.FilePathProperty;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

@Log4j2
@PropertySource("classpath:filepath.property")
@RequestMapping("image")
@Controller
public class ImageController {

    @Value("${image.filepath:}")
    private String imageFilePath;
    @Autowired
    private FilePathProperty filePathProperty;

    @RequestMapping("load")
    @ResponseBody
    public HttpEntity<byte[]> getImage(@RequestParam("name") String fileName) {
        File file = new File(Paths.get(imageFilePath, fileName + ".png").toString());
        log.debug(file.toPath().toUri());
        byte[] imageByte = null;
        try {
            imageByte = Files.readAllBytes(file.toPath());
        } catch (IOException e) {
            return null;
        }
        return new HttpEntity<byte[]>(imageByte);
    }
}
