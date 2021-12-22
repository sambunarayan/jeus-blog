package jp.co.jeus.blog.service;

import jp.co.jeus.blog.domain.posts.PostRepository;
import jp.co.jeus.blog.domain.posts.Post;
import jp.co.jeus.blog.web.dto.PostPageDto;
import jp.co.jeus.blog.web.dto.PostResponseDto;
import jp.co.jeus.blog.web.dto.PostSaveRequestDto;
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
        int fromIdx = (page - 1) * 10;
        int toIdx = (page * 10) < list.size() ? (page * 10) : list.size() - 1;
        List<PostResponseDto> currPageList = list.subList(fromIdx, toIdx)
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
    public PostResponseDto update(PostSaveRequestDto requestDto) {
        Post post = repository.findById(requestDto.getId()).orElseThrow(() -> new IllegalArgumentException("No result record. id = " + requestDto.getId()));
        post.update(requestDto.getTitle(), requestDto.getContent());
        return new PostResponseDto(repository.save(post));
    }

    @Transactional
    public PostResponseDto savePost(Post post) {
        return new PostResponseDto(repository.save(post));
    }
}
