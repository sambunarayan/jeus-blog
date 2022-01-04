package jp.co.jeus.blog.service;

import jp.co.jeus.blog.domain.posts.Board;
import jp.co.jeus.blog.domain.posts.BoardRepository;
import jp.co.jeus.blog.web.dto.BoardResponseDto;
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
public class ITBulletinBoardServiceTest {

    @Autowired
    private ITBulletinBoardService service;
    @Autowired
    private BoardRepository repository;

    @Test
    public void findAll() {
        Board board = service.saveBoard(getBoard());
        List<BoardResponseDto> list = service.findAll();
        assertThat(list.size()).isGreaterThan(0);
        for (BoardResponseDto dto : list) {
            if (dto.getId() == board.getId()) {
                assertThat(dto.getId()).isEqualTo(board.getId());
                assertThat(dto.getBoardName()).isEqualTo(board.getBoardName());
                assertThat(dto.getCategory()).isEqualTo(board.getCategory());
                assertThat(dto.getDescription()).isEqualTo(board.getDescription());
                break;
            }
        }
        service.deleteBoard(board);
    }

    @Test
    public void saveBoard() {
        Board target = getBoard();

        // when
        Board board = service.saveBoard(target);

        // then
        assertThat(target.getId()).isEqualTo(board.getId());
        assertThat(target.getBoardName()).isEqualTo(board.getBoardName());
        assertThat(target.getCategory()).isEqualTo(board.getCategory());
        assertThat(target.getDescription()).isEqualTo(board.getDescription());

        service.deleteBoard(board);
    }

    @Test
    public void deleteBoard() {
        Board board = service.saveBoard(getBoard());

        // when
        service.deleteBoard(board);

        // then
        Optional<Board> opBoard = repository.findById(board.getId());
        assertThat(opBoard.isPresent()).isEqualTo(false);
    }

    private Board getBoard() {
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyyMMddHHmmssSSS");
        String dateTime = fmt.format(LocalDateTime.now());
        return Board.builder()
                .boardName("test" + dateTime)
                .category("it")
                .description("Kubernetes diary.")
                .build();
    }
}
