package jp.co.jeus.blog.form;

import lombok.Getter;
import lombok.Setter;
import org.springframework.validation.ObjectError;

import java.io.Serializable;
import java.util.List;

@Setter
@Getter
public class BoardValidationForm implements Serializable {

    private List<ObjectError> errors;
    private String boardNameInput;
    private String categoryInput;
    private String colorSelect;
    private String detailsArea;

    public BoardValidationForm() {
        this.boardNameInput = "";
        this.categoryInput = "";
        this.colorSelect = "";
        this.detailsArea = "";
    }
}
