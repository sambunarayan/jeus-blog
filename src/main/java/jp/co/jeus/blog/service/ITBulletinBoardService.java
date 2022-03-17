package jp.co.jeus.blog.service;

import jp.co.jeus.blog.domain.posts.Board;
import jp.co.jeus.blog.domain.posts.BoardRepository;
import jp.co.jeus.blog.web.dto.BoardResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@PropertySource("classpath:filepath.property")
@Service
public class ITBulletinBoardService {

    @Autowired
    private BoardRepository repository;

    /**
     * Returns all board data.
     *
     * @return Returns a list of boards from the board table.
     */
    @Transactional
    public List<BoardResponseDto> findAll() {
        return repository.findAll()
                .stream()
                .map(e -> new BoardResponseDto(e))
                .collect(Collectors.toList());
    }

    /**
     * Saves the specified data to the Board table.
     *
     * @param board data to be stored in the Board table
     * @return Returns the board data stored in the board table.
     */
    @Transactional
    public Board saveBoard(Board board) {
        return repository.save(board);
    }

    /**
     * Deletes the specified data to the Board table.
     *
     * @param board data to be deleted in the Board table
     */
    @Transactional
    public void deleteBoard(Board board) {
        repository.delete(board);
    }
}
