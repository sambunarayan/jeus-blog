package jp.co.jeus.blog.web.dto;

import jp.co.jeus.blog.domain.posts.Post;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class PostResponseDto {
    private Long id;
    private String title;
    private String content;
    private String author;
    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;

    public PostResponseDto(Post post) {
        this.id = post.getId();
        this.title = post.getTitle();
        this.content = post.getContent();
        this.author = post.getAuthor();
        this.createdDate = post.getCreatedDate();
        this.modifiedDate = post.getModifiedDate();
    }
}
