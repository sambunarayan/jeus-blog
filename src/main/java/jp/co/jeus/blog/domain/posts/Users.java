package jp.co.jeus.blog.domain.posts;

import com.sun.istack.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Users entity
 */
@Getter
@NoArgsConstructor
@Entity
public class Users {

    @Id
    private String userId;
    @NotNull
    private String password;
    @NotNull
    private String userName;
    private String mail;

    @Builder
    public Users(String userId, String password, String userName, String mail) {
        this.userId = userId;
        this.password = password;
        this.userName = userName;
        this.mail = mail;
    }
}
