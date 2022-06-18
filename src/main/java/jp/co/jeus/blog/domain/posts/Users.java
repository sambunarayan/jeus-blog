package jp.co.jeus.blog.domain.posts;

import com.sun.istack.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Users entity
 */
@Getter
@NoArgsConstructor
@Entity
public class Users {

    /**
     * UserID
     */
    @Id
    private String userId;
    /**
     * Password
     */
    @NotNull
    private String password;
    /**
     * User name
     */
    @NotNull
    private String userName;
    /**
     * mail
     */
    private String mail;

    @Builder
    public Users(String userId, String password, String userName, String mail) {
        this.userId = userId;
        this.password = password;
        this.userName = userName;
        this.mail = mail;
    }
}
