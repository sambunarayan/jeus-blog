package jp.co.jeus.blog.security.dto;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;
import java.util.stream.Collectors;

public class BlogUserDetails extends User {

    private MemberInfoDto member;

    public BlogUserDetails(String username, String password, boolean enabled, boolean accountNonExpired,
                           boolean credentialsNonExpired, boolean accountNonLocked,
                           Collection<? extends GrantedAuthority> authorities) {
        super(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
    }

    public BlogUserDetails(MemberInfoDto dto) {
        super(dto.getUserid(), dto.getUserpw(),
                dto.getAuthList()
                        .stream()
                        .map(auth-> new SimpleGrantedAuthority(auth.getAuth()))
                        .collect(Collectors.toList())
        );
        this.member = dto;
    }

    public MemberInfoDto getMember() {
        return this.member;
    }
}
