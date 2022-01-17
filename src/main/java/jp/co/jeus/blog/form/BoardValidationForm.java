package jp.co.jeus.blog.form;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Setter
@Getter
public class BoardValidationForm implements Serializable {

    private String boardNameInput;
    private String categoryInput;
    private String colorSelect;
    private String detailsArea;
}
