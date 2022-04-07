package jp.co.jeus.blog.advice;

import jp.co.jeus.blog.constants.FileControlResultCode;
import jp.co.jeus.blog.web.dto.ExceptionDto;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.multipart.MultipartException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletRequest;

@Log4j2
@ControllerAdvice
public class FileExceptionHandler extends ResponseEntityExceptionHandler {

    /**
     * MultipartException handling
     *
     * @param request
     * @param ex
     * @return
     */
    @ExceptionHandler(MultipartException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ExceptionDto handleFileException(HttpServletRequest request, Throwable ex) {
        log.error("FileExceptionHandler executed ::::::: {}", ex.toString());
        if (ex instanceof MaxUploadSizeExceededException) {
            return new ExceptionDto(FileControlResultCode.EXCEED_FILE_SIZE.getResultCode()
                    , FileControlResultCode.EXCEED_FILE_SIZE.getDetail());
        } else {
            return new ExceptionDto(FileControlResultCode.UNEXPECTED_ERR.getResultCode(),
                    FileControlResultCode.UNEXPECTED_ERR.getDetail());
        }
    }
}