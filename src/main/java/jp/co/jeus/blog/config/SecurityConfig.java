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
                .antMatchers("/RestImage/**","/it/**", "/images/**", "/js/**", "/app/v1/css/**");
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
        auth.userDetailsService(blogUserDetailService())
                .passwordEncoder(passwordEncoder());
    }

    /**
     * @param http
     * @throws Exception
     */
    protected void configure(HttpSecurity http) throws Exception {
        http
//                .csrf()
//                .disable()
                .authorizeRequests()
                    .mvcMatchers("/**").permitAll()
//                    .mvcMatchers("/it/**").permitAll()
                    .anyRequest()
                    .authenticated()
                .and()
                .formLogin()
//                .loginPage("/accounts/login").permitAll()
//                .defaultSuccessUrl("/")
                .loginProcessingUrl("/accounts/login/proc")
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
