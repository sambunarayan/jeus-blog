package jp.co.jeus.blog.advice;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.UUID;

/**
 * Controller advice
 */
@Log4j2
@ControllerAdvice
public class CustomControllerAdvice {

    /**
     * Init binder
     *
     * @param dataBinder
     */
    @InitBinder
    public void initBinder(WebDataBinder dataBinder) {
        log.debug("initBinder : {}", dataBinder);
        // WebDataBinderのメソッドを呼び出してカスタマイズする
        dataBinder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
    }

    /**
     * ModleAttribute handling
     *
     * @param trackingId
     * @return
     */
    @ModelAttribute("trackingId")
    public String addOneObject(@RequestHeader("X-Tracking-Id") Optional<String> trackingId) {
        // Return the object to be added to Model
        return trackingId.orElse(UUID.randomUUID().toString());
    }
}
