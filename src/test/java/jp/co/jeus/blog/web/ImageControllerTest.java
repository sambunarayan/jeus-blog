package jp.co.jeus.blog.web;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@AutoConfigureMockMvc
@SpringBootTest
public class ImageControllerTest {

    @Autowired
    private MockMvc mvc;

    @Test
    public void getImage() throws Exception {
        mvc.perform(get("/image/load")
                .param("name","test"))
                .andExpect(status().isOk());
    }

    @Test
    public void getLogo() throws Exception {
        mvc.perform(get("/image/logo/test"))
                .andExpect(status().isOk());
    }
}
