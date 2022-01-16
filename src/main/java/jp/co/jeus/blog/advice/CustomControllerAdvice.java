package jp.co.jeus.blog.advice;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.UUID;

@Log4j2
@ControllerAdvice
public class CustomControllerAdvice {

    @InitBinder
    public void initBinder(WebDataBinder dataBinder) {
        log.debug("initBinder : {}", dataBinder);
        // WebDataBinderのメソッドを呼び出してカスタマイズする
        dataBinder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
    }

    @ModelAttribute("trackingId")
    public String addOneObject(@RequestHeader("X-Tracking-Id") Optional<String> trackingId) {
        log.debug("addOneObject : {}", trackingId);
        // Modelに追加するオブジェクトを返却する
        return trackingId.orElse(UUID.randomUUID().toString());
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public String handleSystemException(Exception e) {
        log.error("System Error occurred.", e);
        return "error";
    }
}
