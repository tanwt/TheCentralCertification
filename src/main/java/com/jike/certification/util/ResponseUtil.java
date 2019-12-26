package com.jike.certification.util;

import com.jike.certification.exception.ErrorCode;
import com.jike.certification.model.Response;

/**
 * @author wentong
 */
public class ResponseUtil {
    public static final Response SUCCESS = makeSuccess(null,"请求成功");

    public static <T> Response<T> makeSuccess(T data) {
        return new Response<>(Response.CODE_SUCCEED, "请求成功", data);
    }

    public static <T> Response<T> makeSuccess(T data, String message) {
        return new Response<>(Response.CODE_SUCCEED, message, data);
    }

    public static <T> Response<T> makeFail(String message) {
        return new Response<>(Response.CODE_FAILED, message, null);
    }

    public static <T> Response<T> makeFail(String message, T data) {
        return new Response<>(Response.CODE_FAILED, message, data);
    }

    public static <T> Response<T> makeFail(ErrorCode errorCode) {
        return new Response<>(errorCode.getCode(), errorCode.getMessage());
    }

    public static boolean isSucceed(Response response) {
        return response != null && response.getSuccess();
    }

    public static boolean isFailed(Response response) {
        return !isSucceed(response);
    }
}
