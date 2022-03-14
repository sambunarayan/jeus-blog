package jp.co.jeus.blog.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpEntity;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@AutoConfigureMockMvc
public class ImageLoadServiceTest {

    @Autowired
    private ImageLoadService service;

    @Test
    public void getImage() {
        HttpEntity<byte[]> image = service.getImage("default");
        assertThat(image).isNull();
    }

    @Test
    public void getLogo() {
        HttpEntity<byte[]> logoImage = service.getLogo("default");
        assertThat(logoImage).isNull();
    }
}
