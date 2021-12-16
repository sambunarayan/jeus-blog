package jp.co.jeus.blog.domain.posts;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
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
                .boardName("kubernetes")
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
        assertThat(res.getBoardName()).isEqualTo(post.getBoardName());
        assertThat(res.getId()).isEqualTo(post.getId());
        assertThat(res.getTitle()).isEqualTo(post.getTitle());
        assertThat(res.getContent()).isEqualTo(post.getContent());
        assertThat(res.getAuthor()).isEqualTo(post.getAuthor());

//        repository.delete(post);
    }
}
