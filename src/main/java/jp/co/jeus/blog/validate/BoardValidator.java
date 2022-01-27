package jp.co.jeus.blog.validate;

import jp.co.jeus.blog.form.BoardValidationForm;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Log4j2
@Component
public class BoardValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return BoardValidationForm.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object form, Errors errors) {
        BoardValidationForm validationForm = (BoardValidationForm) form;
        log.debug("::::::::::::::::: validate ::::::::::::::::");
        log.debug("validate ----->" + validationForm.getBoardNameInput());
        if (validationForm.getBoardNameInput() == null || validationForm.getBoardNameInput().isBlank()) {
            errors.rejectValue("BoardNameInput"," is empty");
        } else {
            if (validationForm.getBoardNameInput().length() > 35) {
                errors.rejectValue("BoardNameInput"," is too long");
            }
        }
        if (validationForm.getDetailsArea() == null || validationForm.getDetailsArea().isBlank()) {
            errors.rejectValue("DetailsArea"," is empty");
        }
    }
}
