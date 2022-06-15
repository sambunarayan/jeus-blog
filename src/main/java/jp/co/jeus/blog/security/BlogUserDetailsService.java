package jp.co.jeus.blog.security;

import jp.co.jeus.blog.domain.posts.Users;
import jp.co.jeus.blog.domain.posts.UsersRepository;
import jp.co.jeus.blog.security.dto.BlogUserDetails;
import jp.co.jeus.blog.security.dto.MemberInfoDto;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.ArrayList;
import java.util.Optional;

@Log4j2
public class BlogUserDetailsService implements UserDetailsService {

    @Autowired
    private UsersRepository userRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info("Requested login - {}", username);
        Optional<Users> opUser = userRepo.findById(username);
        if (opUser.isPresent()) {
            Users user = opUser.get();
            MemberInfoDto memberInfoDto = new MemberInfoDto();
            memberInfoDto.setUserid(user.getUserId());
            memberInfoDto.setUsername(user.getUserName());
            memberInfoDto.setUserPw(user.getPassword());
            memberInfoDto.setAuthList(new ArrayList<>());
            return new BlogUserDetails(memberInfoDto);
        }
        return null;
    }
}
