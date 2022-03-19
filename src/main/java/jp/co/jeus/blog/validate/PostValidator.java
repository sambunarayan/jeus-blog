package jp.co.jeus.blog.validate;

import jp.co.jeus.blog.form.PostValidationForm;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Log4j2
@Component
public class PostValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return false;
    }

    @Override
    public void validate(Object form, Errors errors) {
        PostValidationForm validationForm = (PostValidationForm) form;
        log.debug("::::::::::::::: PostValidator :::::::::::::");
        if (validationForm.getTitle() == null || validationForm.getTitle().isEmpty()) {
            log.debug("Tile is empty");
            errors.rejectValue("title"," is empty");
        }
    }
}
