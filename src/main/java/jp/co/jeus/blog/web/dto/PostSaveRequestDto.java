package jp.co.jeus.blog.web.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
public class PostSaveRequestDto {

    private Long id;
    private String title;
    private String content;

    @Builder
    public PostSaveRequestDto(Long id, String title, String content) {
        this.id = id;
        this.title = title;
        this.content = content;
    }
}
