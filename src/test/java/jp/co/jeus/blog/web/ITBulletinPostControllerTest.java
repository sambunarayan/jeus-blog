package jp.co.jeus.blog.web;

import jp.co.jeus.blog.web.dto.PostResponseDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class ITBulletinPostControllerTest {

    @Autowired
    private MockMvc mvc;

    @Test
    public void board() throws Exception {
        String expectedBoardName = "Kubernetes";

        mvc.perform(get("/it/board/post/list/Kubernetes"))
                .andExpect(status().isOk())
                .andExpect(model().attribute("board_name", expectedBoardName));
    }

    @Test
    public void postByGet() throws Exception {
        String boardName = "Kubernetes";
        long page = 1;
        mvc.perform(get("/it/board/post/posting/" + boardName)
                .param("page", String.valueOf(page)))
                .andExpect(status().isOk())
                .andExpect(view().name("it-bulletin-posting"))
                .andExpect(model().attribute("board_name", boardName))
                .andExpect(model().attribute("current_page", page));
    }

    @Test
    public void postByPost() throws Exception {
        String boardName = "Kubernetes";
        String page = "1";
        long id = 1;

        MvcResult result = mvc.perform(post("/it/board/post/posting/" + boardName)
                        .param("boardId", String.valueOf(id))
                        .param("hidden_current_page", page))
                .andExpect(status().isOk())
                .andExpect(view().name("it-bulletin-posting"))
                .andExpect(model().attribute("current_page", page))
                .andReturn();
        // Evaluate response dto.
        assertThat(((PostResponseDto) result.getModelAndView().getModelMap().get("post")).getId()).isEqualTo(id);
    }
}
