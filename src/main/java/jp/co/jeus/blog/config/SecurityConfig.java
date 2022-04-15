package jp.co.jeus.blog.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    public SecurityConfig() {
        super(false);
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.debug(false)
                .ignoring()
                .antMatchers("/images/**", "/js/**", "/css/**");
    }

    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("admin").password("admin").authorities("USER", "ADMIN");
    }

    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/login").permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin()
//                .loginPage("/accounts/login")
                .successForwardUrl("/it/board/main/bulletin")
                .usernameParameter("user")
                .passwordParameter("pwd")
                .and()
                .httpBasic();
    }
}
