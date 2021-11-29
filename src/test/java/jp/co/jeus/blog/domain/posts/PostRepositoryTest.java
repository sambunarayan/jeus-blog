package jp.co.jeus.blog.domain.posts;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@AutoConfigureMockMvc
public class PostRepositoryTest {

    @Autowired
    private PostRepository repository;

    @Test
    public void save() {
        Post post = Post.builder()
                .title("Test title")
                .content("Test content")
                .author("Test Author")
                .build();
        repository.save(post);
    }
}
