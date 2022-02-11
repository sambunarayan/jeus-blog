package jp.co.jeus.blog.domain.posts;

import jp.co.jeus.blog.domain.BaseTimeEntity;
import lombok.*;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class Board extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name="board_name")
    @NonNull
    private String boardName;
    @NonNull
    private String category;
    @NonNull
    private String color;
    private String logo;
    private String description;

    @Builder
    public Board(String boardName, String category, String color, String logo, String description) {
        this.boardName = boardName;
        this.category = category;
        this.color = color;
        this.logo = logo;
        this.description = description;
    }
}
