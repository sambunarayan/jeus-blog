package jp.co.jeus.blog.security.dto;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class MemberInfoDto {

    private String userid;
    private String userpw;
    private String username;
    private boolean enabled;

    private Date regdate;
    private Date updated;
    private List<AuthDto> authList;
}
