package jp.co.jeus.blog.domain.posts;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {
    @Query("SELECT p FROM Post p ORDER BY p.id DESC")
    List<Post> findAllDesc();

    @Query("SELECT p FROM Post p WHERE board_name = ?1 ORDER BY p.id DESC")
    List<Post> findByBoardNameDesc(String boardName);

    @Query(value = "SELECT t.id, t.board_name, t.title, t.content, t.author, t.created_date, t.modified_date FROM " +
            "(SELECT ROW_NUMBER() OVER(ORDER BY id ASC) rn, p.* FROM post p WHERE board_name = '?1') t WHERE t.rn < ('?2' * 10) AND t.rn >= (('?3' - 1) * 10)", nativeQuery = true)
    List<Post> findWithPaging(String boardName, Integer p1, Integer p2);

    @Query("SELECT count(p.id) FROM Post p WHERE board_name = ?1")
    int getCountByBoardName(String boardName);
}
