package jp.co.jeus.blog.service;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.HttpEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

@Log4j2
@Service
@PropertySource("classpath:filepath.property")
public class ImageLoadService {

    @Value("${image.filepath:}")
    private String imageFilePath;
    @Value("${logo.image.filepath:}")
    private String logoPath;

    public HttpEntity<byte[]> getImage(String fileName) {
        try {
            File file = new File(Paths.get(imageFilePath, fileName + ".png").toString());
            log.debug(file.toPath().toUri());
            return new HttpEntity<byte[]>(Files.readAllBytes(file.toPath()));
        } catch (IOException e) {
            return null;
        }
    }

    public HttpEntity<byte[]> getLogo(String logo) {
        try {
            File file = new File(Paths.get(logoPath, logo).toString());
            log.debug(file.toPath().toUri());
            return new HttpEntity<byte[]>(Files.readAllBytes(file.toPath()));
        } catch (IOException e) {
            return null;
        }
    }
}