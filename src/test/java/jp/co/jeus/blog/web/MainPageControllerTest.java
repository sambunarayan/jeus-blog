package jp.co.jeus.blog.web;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@AutoConfigureMockMvc
@SpringBootTest
public class MainPageControllerTest {

    @Autowired
    private MockMvc mvc;

    @Test
    public void index() throws Exception {
        MvcResult result = mvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(view().name("index"))
                .andReturn();
        assertThat(result.getModelAndView().getModel().get("LastIndexId")).isEqualTo(Integer.MAX_VALUE);
        assertThat(result.getModelAndView().getModel().get("aboutMe")).isNotNull();
    }
}
