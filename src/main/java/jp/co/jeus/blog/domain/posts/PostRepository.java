package jp.co.jeus.blog.domain.posts;

import jp.co.jeus.blog.aop.TimerAspect;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

@TimerAspect.Timer
public interface PostRepository extends JpaRepository<Post, Long> {

    @TimerAspect.Timer
    @Query("SELECT p FROM Post p ORDER BY p.id DESC")
    List<Post> findAllDesc();

    @TimerAspect.Timer
    @Query("SELECT p FROM Post p WHERE board_name = ?1 ORDER BY p.id DESC")
    List<Post> findByBoardNameDesc(String boardName);

}
