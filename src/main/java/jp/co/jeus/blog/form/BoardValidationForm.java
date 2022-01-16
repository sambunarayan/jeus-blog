package jp.co.jeus.blog.form;

import lombok.Getter;

import java.io.Serializable;

@Getter
public class BoardValidationForm implements Serializable {
    private String boardNameInput;
    private String categoryInput;
    private String colorSelect;
    private String detailsArea;
}
