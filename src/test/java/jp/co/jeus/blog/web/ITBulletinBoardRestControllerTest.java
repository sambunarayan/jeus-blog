package jp.co.jeus.blog.web;

import com.google.gson.Gson;
import jp.co.jeus.blog.web.dto.PostResponseDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class ITBulletinBoardRestControllerTest {

    @Autowired
    private MockMvc mvc;

    @Test
    public void getLatestPosts() throws Exception {
        int id = Integer.MAX_VALUE;
        String result = mvc.perform(get("/it/board/post/latest/" + id))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();
//        List<PostResponseDto> resList = new Gson().fromJson(result, PostResponseDto.class);
        System.out.println(result);
    }
}
