package jp.co.jeus.blog.domain.posts;

import jp.co.jeus.blog.domain.BaseTimeEntity;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import javax.persistence.Entity;

@Getter
@RequiredArgsConstructor
@Entity
public class Board extends BaseTimeEntity {
}
