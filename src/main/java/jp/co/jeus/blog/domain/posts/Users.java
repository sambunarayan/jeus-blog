package jp.co.jeus.blog.domain.posts;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import javax.persistence.Entity;
import javax.persistence.Id;

@Getter
@NoArgsConstructor
@Entity
public class Users {

    @Id
    private String userId;
    private String password;
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
