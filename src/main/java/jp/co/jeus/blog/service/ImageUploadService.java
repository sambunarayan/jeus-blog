package jp.co.jeus.blog.service;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.UUID;

@Log4j2
@PropertySource("classpath:filepath.property")
@Service
public class ImageUploadService {

    @Value("${logo.image.filepath:}")
    private String logoPath;

    public String uploadLogImage(MultipartFile logoFiles) {
        if (logoFiles == null) {
            return "";
        }
        UUID logoFileName = UUID.randomUUID();
        log.debug("Logo original file name : " + logoFiles.getOriginalFilename());
        log.debug("Logo fileSize : " + logoFiles.getSize());
        log.debug("New logo file name : " + logoFileName.toString());

        File saveFile = new File(logoPath, logoFileName.toString());
        try {
            logoFiles.transferTo(saveFile);
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return logoFileName.toString();
    }
}
