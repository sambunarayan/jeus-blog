package jp.co.jeus.blog.web;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class ITBulletinBoardControllerTest {

    @Autowired
    private MockMvc mvc;

    @Test
    public void findAll() throws Exception {
        mvc.perform(get("/it/board/bulletin"))
                .andExpect(status().isOk());
    }

    @Test
    public void register() throws Exception {
        mvc.perform(get("/it/board/register"))
                .andExpect(status().isOk())
                .andExpect(view().name("it-bulletin-board-register"));
    }

    @Test
    public void board() throws Exception {
        String expectedBoardName = "Kubernetes";

        mvc.perform(get("/it/board/list/Kubernetes"))
                .andExpect(status().isOk())
                .andExpect(model().attribute("board_name", expectedBoardName));
    }
}
