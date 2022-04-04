package jp.co.jeus.blog.web.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class ExceptionDto {

    private String errorCode;
    private String detail;
}
