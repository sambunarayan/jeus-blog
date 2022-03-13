package jp.co.jeus.blog.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@AutoConfigureMockMvc
public class ImageUploadServiceTest {

    @Autowired
    private ImageUploadService service;

    @Test
    public void uploadLogImage() {
        String logoImageName = service.uploadLogImage(null);
        assertThat(logoImageName).isEqualTo("default");
    }
}
