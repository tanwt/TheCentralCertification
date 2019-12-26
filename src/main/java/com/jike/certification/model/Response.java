package com.jike.certification.model;

import java.io.Serializable;
/**
 * @author wentong
 * @date 2019-12-26
 */
public class Response<T> implements Serializable {
    private static final long serialVersionUID = 1998307887673028548L;

    public static final int CODE_SUCCEED = 0;
    public static final int CODE_FAILED = 1;

    private boolean success = false;
    private int code = 1;
    private String message = "";
    private T data;

    public Response() {
    }

    public Response(int code, String message) {
        this.code = code;
        this.message = message;
        this.success = code == CODE_SUCCEED;
    }

    public Response(int code, String message, T data) {
        this(code, message);
        this.data = data;
    }

    public boolean getSuccess() {
        return success;
    }

    public Response<T> setSuccess(boolean success) {
        this.success = success;
        return this;
    }

    public int getCode() {
        return code;
    }

    public Response<T> setCode(int code) {
        this.code = code;
        return this;
    }

    public String getMessage() {
        return message;
    }

    public Response<T> setMessage(String message) {
        this.message = message;
        return this;
    }

    public T getData() {
        return data;
    }

    public Response<T> setData(T data) {
        this.data = data;
        return this;
    }
}
