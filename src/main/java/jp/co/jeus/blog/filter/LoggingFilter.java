package jp.co.jeus.blog.filter;

import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@Log4j2
@Component
public class LoggingFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        log.debug(((HttpServletRequest) request).getRequestURI() + " [START]");
        chain.doFilter(request, response);
        log.debug(((HttpServletRequest) request).getRequestURI() + " [END]");
    }
}
