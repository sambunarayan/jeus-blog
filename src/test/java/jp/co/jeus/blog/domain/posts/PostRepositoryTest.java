package jp.co.jeus.blog.domain.posts;

import org.assertj.core.api.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@AutoConfigureMockMvc
public class PostRepositoryTest {

    @Autowired
    private PostRepository repository;

    @Test
    public void save() {
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyyMMddHHmmssSSS");
        String dateTime = fmt.format(LocalDateTime.now());
        Post post = Post.builder()
                .title("Test title " + dateTime)
                .content("Test content " + dateTime)
                .author("Test Author")
                .build();
        post = repository.save(post);

        // when
        Optional<Post> resOp = repository.findById(post.getId());
        assertThat(!resOp.isPresent());

        // then
        Post res = resOp.get();
        assertThat(res.getId()).isEqualTo(post.getId());
        assertThat(res.getTitle()).isEqualTo(post.getTitle());
        assertThat(res.getContent()).isEqualTo(post.getContent());
        assertThat(res.getAuthor()).isEqualTo(post.getAuthor());

//        repository.delete(post);
    }
}
