package jp.co.jeus.blog.listener;

import lombok.extern.log4j.Log4j2;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import javax.servlet.http.HttpServletRequest;
import java.util.Locale;

@Log4j2
@Component
public class CustomServletRequestListener implements ServletRequestListener {

    private final String systemName;

    public CustomServletRequestListener(MessageSource messageSource) {
        this.systemName = messageSource.getMessage("system.name", null, "jeus-blog", Locale.getDefault());
    }

    @Override
    public void requestInitialized(ServletRequestEvent sre) {
        // when request received
        log.debug(((HttpServletRequest) sre.getServletRequest()).getRequestURI() + " [START]");
    }

    @Override
    public void requestDestroyed(ServletRequestEvent sre) {
        // when request destroyed
        log.debug(((HttpServletRequest) sre.getServletRequest()).getRequestURI() + " [END]");
    }
}
