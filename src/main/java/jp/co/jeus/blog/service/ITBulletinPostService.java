package jp.co.jeus.blog.service;

import jp.co.jeus.blog.domain.posts.PostRepository;
import jp.co.jeus.blog.domain.posts.Post;
import jp.co.jeus.blog.web.dto.PostPageDto;
import jp.co.jeus.blog.web.dto.PostResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class ITBulletinPostService {
    @Autowired
    private PostRepository repository;

    @Transactional
    public PostPageDto findWithPaging(String boardName, int page) {
        List<Post> list = repository.findByBoardNameDesc(boardName);
        List<PostResponseDto> currPageList = list.subList((page - 1) * 10, page * 10)
                .stream()
                .map(p -> new PostResponseDto(p))
                .collect(Collectors.toList());
        return new PostPageDto(list.size(), currPageList);
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
    public Post savePost(Post post) {
        return repository.save(post);
    }
}