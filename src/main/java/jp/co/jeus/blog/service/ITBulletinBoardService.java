package jp.co.jeus.blog.service;

import jp.co.jeus.blog.domain.posts.PostRepository;
import jp.co.jeus.blog.domain.posts.Post;
import jp.co.jeus.blog.web.dto.PostResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ITBulletinBoardService {
    @Autowired
    private PostRepository repository;

    public List<Post> findAll() {
        return repository.findAll();
    }

    public List<PostResponseDto> findAllDesc() {
        return repository.findAllDesc().stream()
                .map(p -> new PostResponseDto(p))
                .collect(Collectors.toList());
    }

    public PostResponseDto findById(Long id) {
        Post post = repository.findById(id).orElseThrow(() -> new IllegalArgumentException("No result record. id = " + id));
        return new PostResponseDto(post);
    }
}
