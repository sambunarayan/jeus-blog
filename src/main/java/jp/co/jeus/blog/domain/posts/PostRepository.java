package jp.co.jeus.blog.domain.posts;

import jp.co.jeus.blog.aop.TimerAspect;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

@TimerAspect.Timer
public interface PostRepository extends JpaRepository<Post, Long> {

    /**
     * Returns a list of Posts sorted in descending order.
     *
     * @return Returns a list of Posts sorted in descending order.
     */
    @TimerAspect.Timer
    @Query("SELECT p FROM Post p ORDER BY p.id DESC")
    List<Post> findAllDesc();

    /**
     * Returns a list of posts sorted in descending order by the specified board name.
     *
     * @param boardName the specified board name
     * @return Returns a list of posts sorted in descending order by the specified board name.
     */
    @TimerAspect.Timer
    @Query("SELECT p FROM Post p WHERE board_name = ?1 ORDER BY p.id DESC")
    List<Post> findByBoardNameDesc(String boardName);

}
