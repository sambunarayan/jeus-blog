package jp.co.jeus.blog.web.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
public class PostSaveRequestDto {

    private Long id;
    private String boardNme;
    private String title;
    private String content;

    @Builder
    public PostSaveRequestDto(Long id, String boardNme, String title, String content) {
        this.id = id;
        this.boardNme = boardNme;
        this.title = title;
        this.content = content;
    }
}
