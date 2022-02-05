package jp.co.jeus.blog.properties;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Getter
@Component
@PropertySource("classpath:filepath.property")
public class FilePathProperty {

    @Value("${image.filepath:")
    private String imageFilePath;
}
