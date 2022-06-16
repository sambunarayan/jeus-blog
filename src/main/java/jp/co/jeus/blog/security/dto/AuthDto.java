package jp.co.jeus.blog.security.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AuthDto {

    private String userid;
    private String auth;
}
