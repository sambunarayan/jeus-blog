package jp.co.jeus.blog.service;

import jp.co.jeus.blog.domain.posts.PostRepository;
import jp.co.jeus.blog.domain.posts.Post;
import jp.co.jeus.blog.web.dto.PostResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ITBulletinBoardService {
    @Autowired
    private PostRepository repository;

    public PostResponseDto findById(Long id) {
        Post post = repository.findById(id).orElseThrow(() -> new IllegalArgumentException("No result record. id = " + id));
        return new PostResponseDto(post);
    }
}
