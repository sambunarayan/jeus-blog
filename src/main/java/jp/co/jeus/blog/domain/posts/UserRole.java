package jp.co.jeus.blog.domain.posts;

import com.sun.istack.NotNull;
import lombok.Getter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Role entity
 */
@Getter
@Entity
public class UserRole {

    @Id
    private String userId;
    @NotNull
    private String role;
}
