package jp.co.jeus.blog.service;

import jp.co.jeus.blog.domain.posts.Board;
import jp.co.jeus.blog.domain.posts.BoardRepository;
import jp.co.jeus.blog.web.dto.BoardResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class ITBulletinBoardService {

    @Autowired
    private BoardRepository repository;

    public List<BoardResponseDto> findAll() {
        return repository.findAll()
                .stream()
                .map(e -> new BoardResponseDto(e))
                .collect(Collectors.toList());
    }

    @Transactional
    public Board saveBoard(Board board) {
        return repository.save(board);
    }
}
