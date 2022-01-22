package jp.co.jeus.blog.web;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

@RequestMapping("image")
@Controller
public class ImageController {

    @RequestMapping("load")
    @ResponseBody
    public HttpEntity<byte[]> getImage(@RequestParam("name") String fileName) {
        File file = new File("D:\\upload\\" + fileName + ".png");

        byte[] imageByte = null;
        HttpHeaders headers = null;
        try {
            imageByte = Files.readAllBytes(file.toPath());
        } catch (IOException e) {
            return null;
        }
        return new HttpEntity<byte[]>(imageByte, headers);
    }
}
