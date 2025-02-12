package yeo.nuel.lix.exception;

import static yeo.nuel.lix.exception.ErrorCode.*;

import lombok.Getter;

@Getter
public class UserException extends RuntimeException {

    private final ErrorCode errorCode;

    public UserException(ErrorCode errorCode) {
        this.errorCode = errorCode;
    }

    public static class UserDoesNotExistException extends UserException {
        public UserDoesNotExistException() {
            super(USER_DOES_NOT_EXIST);
        }
    }

    public static class UserAlreadyExistsException extends UserException {
        public UserAlreadyExistsException() {
            super(USER_ALREADY_EXISTS);
        }
    }
}
