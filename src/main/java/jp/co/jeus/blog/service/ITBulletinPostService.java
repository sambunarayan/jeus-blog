package jp.co.jeus.blog.service;

import jp.co.jeus.blog.domain.posts.BoardRepository;
import jp.co.jeus.blog.domain.posts.PostRepository;
import jp.co.jeus.blog.domain.posts.Post;
import jp.co.jeus.blog.utils.RequestBodyConvertUtility;
import jp.co.jeus.blog.web.dto.PostPageDto;
import jp.co.jeus.blog.web.dto.PostResponseDto;
import jp.co.jeus.blog.web.dto.PostSaveRequestDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Log4j2
@RequiredArgsConstructor
@Service
public class ITBulletinPostService {

    @Autowired
    private PostRepository repository;
    @Autowired
    private BoardRepository boardRepository;

    /**
     *
     * @return
     */
    @Transactional
    public List<PostResponseDto> findLatestPost(Long id) {
        List<PostResponseDto> latestPosts = new ArrayList<>();
        List<Post> posts = id <= 0 ? repository.findAllDesc() : repository.findLatestPosts(id);
        Map<String, String> colorMap = boardRepository
                .findAll()
                .stream()
                .collect(Collectors.toMap(b -> b.getBoardName(), b -> b.getColor()));
        log.debug(posts);
        for (Post post : posts) {
            latestPosts.add(new PostResponseDto(post, colorMap.get(post.getBoardName())));
            if (latestPosts.size() >= 5) {
                break;
            }
        }
        return latestPosts;
    }

    /**
     *
     * @param boardName
     * @param page
     * @return
     */
    @Transactional
    public PostPageDto findWithPaging(String boardName, int page) {
        List<Post> list = repository.findByBoardNameDesc(boardName);
        if (list == null || list.isEmpty()) {
            return PostPageDto.builder()
                    .totalPostNum(0)
                    .posts(new ArrayList<>())
                    .build();
        }
        int fromIdx = (page - 1) * 10;
        int toIdx = (page * 10) < list.size() ? (page * 10) : list.size();
        List<PostResponseDto> currPageList = list.subList(fromIdx, toIdx)
                .stream()
                .map(p -> new PostResponseDto(p))
                .collect(Collectors.toList());
        return new PostPageDto(list.size(), currPageList);
    }

    /**
     *
     * @param boardName
     * @return
     */
    @Transactional
    public List<PostResponseDto> findByBoardNameDesc(String boardName) {
        return repository.findByBoardNameDesc(boardName).stream()
                .map(p -> new PostResponseDto(p))
                .collect(Collectors.toList());
    }

    /**
     *
     * @param id
     * @return
     */
    @Transactional
    public PostResponseDto findById(Long id) {
        Post post = repository.findById(id).orElseThrow(() -> new IllegalArgumentException("No result record. id = " + id));
        return new PostResponseDto(post, post.getId(), RequestBodyConvertUtility.convertSpaceNBlank(post.getContent()));
    }

    /**
     *
     * @param requestDto
     * @return
     */
    @Transactional
    public PostResponseDto update(PostSaveRequestDto requestDto) {
        Post post = repository.findById(requestDto.getId()).orElseThrow(() -> new IllegalArgumentException("No result record. id = " + requestDto.getId()));
        post.update(requestDto.getTitle(), requestDto.getContent());
        return new PostResponseDto(repository.save(post));
    }

    /**
     *
     * @param post
     * @return
     */
    @Transactional
    public PostResponseDto savePost(Post post) {
        return new PostResponseDto(repository.save(post));
    }

    /**
     *
     * @param id
     */
    @Transactional
    public void deletePost(Long id) {
        Post post = repository.findById(id).orElseThrow(() -> new IllegalArgumentException("No data. id=" + id));
        repository.delete(post);
    }
}
