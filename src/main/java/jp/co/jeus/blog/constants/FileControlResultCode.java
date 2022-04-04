package jp.co.jeus.blog.constants;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum FileControlResultCode {
    /**
     * OK
     */
    OK("00","OK"),
    /**
     * Exceeds the maximum file size
     */
    EXCEED_FILE_SIZE("01","Exceeds the maximum file size."),
    /**
     * Unexpected error
     */
    UNEXPECTED_ERR("99", "An unexpected error occurred."),;

    private final String resultCode;
    private final String detail;
}
