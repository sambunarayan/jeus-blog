package jp.co.jeus.blog.domain.posts;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {
    @Query("SELECT p FROM Post p ORDER BY p.id DESC")
    List<Post> findAllDesc();

    @Query("SELECT p FROM Post p WHERE board_name = ?1 ORDER BY p.id DESC")
    List<Post> findByBoardNameDesc(String boardName);

    @Query(value = "SELECT * FROM Post WHERE board_name = ?1 AND id > ?2 LIMIT 10", nativeQuery = true)
    List<Post> findWithPaging(String boardName, Long id);

    @Query("SELECT count(p.id) FROM Post p WHERE board_name = ?1")
    int getCountByBoardName(String boardName);
}
