package jp.co.jeus.blog.config;

import jp.co.jeus.blog.security.BlogUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.sql.DataSource;

/**
 * SecurityConfig
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private DataSource dataSource;

    public SecurityConfig() {
        super(false);
    }

    /**
     * WebSecurtiy configuration
     *
     * @param web
     * @throws Exception
     */
    @Override
    public void configure(WebSecurity web) throws Exception {
        web.debug(false)
                .ignoring()
                .antMatchers("/images/**", "/js/**", "/css/**");
    }

    /**
     * Password encoder
     *
     * @return
     */
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public UserDetailsService blogUserDetailService() {
        return new BlogUserDetailsService();
    }
    /**
     * Authentication configuration
     *
     * @param auth
     * @throws Exception
     */
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.jdbcAuthentication()
//                .passwordEncoder(passwordEncoder())
//                .dataSource(dataSource)
//                .usersByUsernameQuery(
//                        "SELECT userId, password, 'true' as enabled from users where userId = ?"
//                ).authoritiesByUsernameQuery(
//                        "SELECT users.userId, user_role.role as authorities " +
//                                "FROM users, user_role " +
//                                "WHERE users.userId = ? AND users.userId = user_role.userId"
//                );
        auth.userDetailsService(blogUserDetailService())
                .passwordEncoder(passwordEncoder());
    }

    /**
     *
     * @param http
     * @throws Exception
     */
    protected void configure(HttpSecurity http) throws Exception {
        http
//                .csrf()
//                .disable()
                .authorizeRequests()
//                .antMatchers("/**").permitAll()
                .antMatchers("/member/**").access("hasRole('ROLE_ADMIN')")
                .and()
                .formLogin()
                .loginPage("/accounts/login")
                .defaultSuccessUrl("/")
                .loginProcessingUrl("/accounts/login/proc")
                .failureForwardUrl("/error")
                .and()
                .exceptionHandling()
                .and()
                .servletApi()
                .and()
                .httpBasic()
                .and()
                .headers();
    }
}
