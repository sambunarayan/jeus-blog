package jp.co.jeus.blog.service;

import jp.co.jeus.blog.domain.posts.PostRepository;
import jp.co.jeus.blog.domain.posts.Post;
import jp.co.jeus.blog.web.dto.PostResponseDto;
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
    private PostRepository repository;

    @Transactional
    public List<Post> findAll() {
        return repository.findAll();
    }

    @Transactional
    public List<PostResponseDto> findAllDesc() {
        return repository.findAllDesc().stream()
                .map(p -> new PostResponseDto(p))
                .collect(Collectors.toList());
    }

    @Transactional
    public List<PostResponseDto> findByBoardNameDesc(String boardName) {
        return repository.findByBoardNameDesc(boardName).stream()
                .map(p -> new PostResponseDto(p))
                .collect(Collectors.toList());
    }

    @Transactional
    public PostResponseDto findById(Long id) {
        Post post = repository.findById(id).orElseThrow(() -> new IllegalArgumentException("No result record. id = " + id));
        return new PostResponseDto(post);
    }

    @Transactional
    public void savePost(Post post) {
        repository.save(post);
    }
}
