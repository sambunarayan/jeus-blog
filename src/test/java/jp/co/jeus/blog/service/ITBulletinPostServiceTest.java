package jp.co.jeus.blog.service;

import jp.co.jeus.blog.domain.posts.Board;
import jp.co.jeus.blog.domain.posts.BoardRepository;
import jp.co.jeus.blog.domain.posts.Post;
import jp.co.jeus.blog.web.dto.PostResponseDto;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@AutoConfigureMockMvc
public class ITBulletinPostServiceTest {

    @Autowired
    private BoardRepository boardRepository;
    @Autowired
    private ITBulletinPostService service;
    private Board board;

    @BeforeEach
    public void setup() {
        board = Board.builder()
                .boardName("UnitTest")
                .category("it")
                .logo("logo")
                .color("black")
                .description("JUnit Test board")
                .build();
        boardRepository.save(board);
    }

    @AfterEach
    public void afterEach() {
        boardRepository.delete(board);
    }

    @Test
    public void savePost() {
        Post post = getNewPost();
        PostResponseDto responseDto = service.savePost(post);

        assertThat(responseDto.getBoardName()).isEqualTo(post.getBoardName());
        assertThat(responseDto.getTitle()).isEqualTo(post.getTitle());
        assertThat(responseDto.getContent()).isEqualTo(post.getContent());
        assertThat(responseDto.getAuthor()).isEqualTo(post.getAuthor());
        service.deletePost(responseDto.getId());
    }

    @Test
    public void findLatestPost() {
        Post post = getNewPost();
        PostResponseDto responseDto = service.savePost(post);

        List<PostResponseDto> resList = service.findLatestPost(-1L);
        assertThat(resList.get(0).getId()).isEqualTo(responseDto.getId());
        assertThat(resList.get(0).getBoardName()).isEqualTo(responseDto.getBoardName());
        assertThat(resList.get(0).getTitle()).isEqualTo(responseDto.getTitle());
        assertThat(resList.get(0).getContent()).isEqualTo(responseDto.getContent());
        assertThat(resList.get(0).getAuthor()).isEqualTo(responseDto.getAuthor());
        service.deletePost(responseDto.getId());
    }

    private Post getNewPost() {
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyyMMddHHmmssSSS");
        String dateTime = fmt.format(LocalDateTime.now());
        return Post.builder()
                .boardName(board.getBoardName())
                .title("Test title " + dateTime)
                .content("Test content " + dateTime)
                .author("Test Author")
                .build();

    }
}
