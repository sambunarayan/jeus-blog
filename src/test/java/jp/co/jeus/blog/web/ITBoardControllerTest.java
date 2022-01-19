package jp.co.jeus.blog.web;

import jp.co.jeus.blog.web.dto.BoardResponseDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class ITBoardControllerTest {

    @Autowired
    private MockMvc mvc;

    @Test
    public void findAll() throws Exception {
        // when
        MvcResult result = mvc.perform(get("/it/board/main/bulletin"))
                .andExpect(status().isOk())
                .andExpect(view().name("it-bulletin"))
                .andReturn();
        // get response dto list
        List<BoardResponseDto> list = (List<BoardResponseDto>) result.getModelAndView().getModel().get("boards");
        // then
        assertThat(list.size()).isGreaterThan(0);
    }

    @Test
    public void register() throws Exception {
        mvc.perform(get("/it/board/main/register"))
                .andExpect(status().isOk())
                .andExpect(view().name("it-bulletin-board-register"));
    }

}
