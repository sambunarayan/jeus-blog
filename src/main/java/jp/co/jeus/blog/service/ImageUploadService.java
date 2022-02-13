package jp.co.jeus.blog.service;

import lombok.extern.log4j.Log4j2;
import net.coobird.thumbnailator.Thumbnailator;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.util.UUID;

@Log4j2
@PropertySource("classpath:filepath.property")
@Service
public class ImageUploadService {

    @Value("${logo.image.filepath:}")
    private String logoPath;

    public String uploadLogImage(MultipartFile logoFile) {
        if (logoFile == null) {
            return "";
        }
        UUID logoFileName = UUID.randomUUID();
        log.debug("Logo original file name : " + logoFile.getOriginalFilename());
        log.debug("Logo fileSize : " + logoFile.getSize());
        log.debug("New logo file name : " + logoFileName.toString());

        File saveFile = new File(logoPath, logoFileName.toString());
        try (FileOutputStream thumbnail = new FileOutputStream(saveFile);) {
//            logoFiles.transferTo(saveFile);
            Thumbnailator.createThumbnail(logoFile.getInputStream(), thumbnail, 250, 250);
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return logoFileName.toString();
    }
}
