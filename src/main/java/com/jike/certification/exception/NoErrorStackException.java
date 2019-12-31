package com.jike.certification.exception;

/**
 * 约定：比较成熟的错误，不需要打印异常栈的，通过本类抛出
 *
 * @author wxl
 */
public class NoErrorStackException extends RuntimeException {

    private int code;
    private String errorMsg;

    public NoErrorStackException(ErrorCode errorCode) {
        this(errorCode.getCode(), errorCode.getMessage());
    }

    public NoErrorStackException(int code, String errorMsg) {
        this.code = code;
        this.errorMsg = errorMsg;
    }

    public NoErrorStackException(String errorMsg) {
        this.code = 1;
        this.errorMsg = errorMsg;
    }

    public int getCode() {
        return code;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    @Override
    public String getMessage() {
        return "[" + this.code + "]" + this.errorMsg;
    }

}
