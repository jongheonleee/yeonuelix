package yeo.nuel.lix.controller;

import yeo.nuel.lix.exception.ErrorCode;

public record YeonuelixApiResponse<T>(
        boolean success,
        String code,
        String message,
        T data
) {
    public static final String CODE_SUCCEED= "SUCCEED";

    public static <T> YeonuelixApiResponse<T> ok(T data) {
        return new YeonuelixApiResponse<>(true, CODE_SUCCEED, null, data);
    }

    public static <T> YeonuelixApiResponse<T> fail(ErrorCode errorCode, String message) {
        return new YeonuelixApiResponse<>(false, errorCode.getCode(), message, null);
    }
}
