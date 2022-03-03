package jp.co.jeus.blog.web;

import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import jp.co.jeus.blog.web.dto.PostResponseDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import java.lang.reflect.Type;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
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
        Gson gson = new GsonBuilder().registerTypeAdapter(LocalDateTime.class, new JsonDeserializer<LocalDateTime>() {
            @Override
            public LocalDateTime deserialize(JsonElement json, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
                return LocalDateTime.parse(json.getAsJsonPrimitive().getAsString());
            }
        }).create();
        List<PostResponseDto> resList = gson.fromJson(result, new TypeToken<ArrayList<PostResponseDto>>(){}.getType());
        resList.forEach(System.out::println);
    }
}
