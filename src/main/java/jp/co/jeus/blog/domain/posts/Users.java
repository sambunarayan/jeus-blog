package jp.co.jeus.blog.domain.posts;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;

@Getter
@NoArgsConstructor
@Entity
public class Users {

    private String userId;
    private String password;
    private String userName;
    private String gmail;
}
