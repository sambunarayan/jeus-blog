package jp.co.jeus.blog.domain.posts;

import jp.co.jeus.blog.domain.BaseTimeEntity;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;

@Getter
@RequiredArgsConstructor
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
    private String description;

}
