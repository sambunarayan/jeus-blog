package jp.co.jeus.blog.domain.posts;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Board repository
 */
public interface BoardRepository extends JpaRepository<Board, Long> {
}
