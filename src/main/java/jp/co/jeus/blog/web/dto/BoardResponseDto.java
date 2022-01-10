package jp.co.jeus.blog.web.dto;

import jp.co.jeus.blog.domain.posts.Board;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class BoardResponseDto {
    private Long id;
    private String boardName;
    private String category;
    private String color;
    private String description;
    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;

    public BoardResponseDto(Board board) {
        this.id = board.getId();
        this.boardName = board.getBoardName();
        this.category = board.getCategory();
        this.color = board.getColor();
        this.description = board.getDescription();
        this.createdDate = board.getCreatedDate();
        this.modifiedDate = board.getModifiedDate();
    }
}
