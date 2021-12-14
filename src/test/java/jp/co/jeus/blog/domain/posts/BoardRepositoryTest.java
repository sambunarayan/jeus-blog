package jp.co.jeus.blog.domain.posts;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@AutoConfigureMockMvc
public class BoardRepositoryTest {

    @Autowired
    private BoardRepository repository;

    @Test
    public void save() {
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyyMMddHHmmssSSS");
        String dateTime = fmt.format(LocalDateTime.now());
        Board board = Board.builder()
                .boardName("test" + dateTime)
                .category("it")
                .description("Kubernetes diary.")
                .build();
        board = repository.save(board);

        // when
        Optional<Board> resOp = repository.findById(board.getId());
        assertThat(!resOp.isPresent());

        // then
        Board res = resOp.get();
        assertThat(res.getBoardName()).isEqualTo(board.getBoardName());
        assertThat(res.getId()).isEqualTo(board.getId());
        assertThat(res.getBoardName()).isEqualTo(board.getBoardName());
        assertThat(res.getCategory()).isEqualTo(board.getCategory());
        assertThat(res.getDescription()).isEqualTo(board.getDescription());

//        repository.delete(board);
    }

}
