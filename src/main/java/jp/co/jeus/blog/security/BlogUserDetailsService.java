package jp.co.jeus.blog.security;

import jp.co.jeus.blog.domain.posts.UserRole;
import jp.co.jeus.blog.domain.posts.UserRoleRepository;
import jp.co.jeus.blog.domain.posts.Users;
import jp.co.jeus.blog.domain.posts.UsersRepository;
import jp.co.jeus.blog.security.dto.AuthDto;
import jp.co.jeus.blog.security.dto.BlogUserDetails;
import jp.co.jeus.blog.security.dto.MemberInfoDto;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Log4j2
public class BlogUserDetailsService implements UserDetailsService {

    @Autowired
    private UsersRepository userRepo;
    @Autowired
    private UserRoleRepository userRoleRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info("Requested login - {}", username);
        Optional<Users> opUser = userRepo.findById(username);
        if (opUser.isPresent()) {
            Optional<UserRole> opRole = userRoleRepo.findById(username);
            List<AuthDto> authList = new ArrayList<>();
            if (opRole.isPresent()) {
                authList.add(new AuthDto(username, opRole.get().getRole()));
            }

            Users user = opUser.get();
            MemberInfoDto memberInfoDto = new MemberInfoDto();
            memberInfoDto.setUserid(user.getUserId());
            memberInfoDto.setUsername(user.getUserName());
            memberInfoDto.setUserPw(user.getPassword());
            memberInfoDto.setAuthList(authList);
            return new BlogUserDetails(memberInfoDto);
        }
        return null;
    }
}
