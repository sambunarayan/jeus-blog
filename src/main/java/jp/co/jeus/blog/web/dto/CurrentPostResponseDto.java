package jp.co.jeus.blog.web.dto;

import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
public class CurrentPostResponseDto {
    private Long id;
    private String title;
    private List<String> content;
    private String author;
    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;

    public CurrentPostResponseDto(PostResponseDto resDto) {
        this.id = resDto.getId();
        this.title = resDto.getTitle();
        this.content = List.of(resDto.getContent().split(System.lineSeparator()));
        this.author = resDto.getAuthor();
        this.createdDate = resDto.getCreatedDate();
        this.modifiedDate = resDto.getModifiedDate();
    }
}
