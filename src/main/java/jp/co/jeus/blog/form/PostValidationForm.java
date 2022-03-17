package jp.co.jeus.blog.form;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Setter
@Getter
public class PostValidationForm implements Serializable {
    private Long id;
    private String title;
    private String content;
}
