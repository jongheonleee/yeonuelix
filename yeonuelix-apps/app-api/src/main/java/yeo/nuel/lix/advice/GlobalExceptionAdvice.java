package yeo.nuel.lix.advice;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import yeo.nuel.lix.controller.YeonuelixApiResponse;
import yeo.nuel.lix.exception.ErrorCode;
import yeo.nuel.lix.exception.UserException;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionAdvice {


    @ExceptionHandler(UserException.class)
    protected YeonuelixApiResponse<?> handleUserException(UserException e) {
        log.error("error={}", e.getMessage(), e);
        return YeonuelixApiResponse.fail(e.getErrorCode(), e.getMessage());
    }

    @ExceptionHandler(RuntimeException.class)
    protected YeonuelixApiResponse<?> handleRuntimeException(RuntimeException e) {
        log.error("error={}", e.getMessage(), e);
        return YeonuelixApiResponse.fail(ErrorCode.DEFAULT_ERROR, e.getMessage());
    }
}
