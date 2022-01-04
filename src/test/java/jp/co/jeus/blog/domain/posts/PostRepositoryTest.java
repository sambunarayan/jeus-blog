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
    public void saveAndDelete() {
        Post post = repository.save(getNewPost());

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

        repository.delete(post);
    }

    @Test
    public void findDesc() {
        Post post1 = repository.save(getNewPost());
        Post post2 = repository.save(getNewPost());

        List<Post> list = repository.findAllDesc();
        assertThat(list.get(0).getId()).isEqualTo(post2.getId());
        assertThat(list.get(0).getBoardName()).isEqualTo(post2.getBoardName());
        assertThat(list.get(0).getTitle()).isEqualTo(post2.getTitle());
        assertThat(list.get(0).getContent()).isEqualTo(post2.getContent());
        assertThat(list.get(0).getAuthor()).isEqualTo(post2.getAuthor());
        assertThat(list.get(1).getId()).isEqualTo(post1.getId());
        assertThat(list.get(1).getBoardName()).isEqualTo(post1.getBoardName());
        assertThat(list.get(1).getTitle()).isEqualTo(post1.getTitle());
        assertThat(list.get(1).getContent()).isEqualTo(post1.getContent());
        assertThat(list.get(1).getAuthor()).isEqualTo(post1.getAuthor());

        list = repository.findByBoardNameDesc("Test");
        assertThat(list.get(0).getId()).isEqualTo(post2.getId());
        assertThat(list.get(0).getBoardName()).isEqualTo(post2.getBoardName());
        assertThat(list.get(0).getTitle()).isEqualTo(post2.getTitle());
        assertThat(list.get(0).getContent()).isEqualTo(post2.getContent());
        assertThat(list.get(0).getAuthor()).isEqualTo(post2.getAuthor());
        assertThat(list.get(1).getId()).isEqualTo(post1.getId());
        assertThat(list.get(1).getBoardName()).isEqualTo(post1.getBoardName());
        assertThat(list.get(1).getTitle()).isEqualTo(post1.getTitle());
        assertThat(list.get(1).getContent()).isEqualTo(post1.getContent());
        assertThat(list.get(1).getAuthor()).isEqualTo(post1.getAuthor());

        repository.delete(post1);
        repository.delete(post2);
    }

    private Post getNewPost() {
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyyMMddHHmmssSSS");
        String dateTime = fmt.format(LocalDateTime.now());
        return Post.builder()
                .boardName("Test")
                .title("Test title " + dateTime)
                .content("Test content " + dateTime)
                .author("Test Author")
                .build();

    }
}
