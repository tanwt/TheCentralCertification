package com.jike.certification.util;

import com.jike.certification.exception.ApiParameterException;
import com.jike.certification.exception.ErrorCode;
import com.jike.certification.exception.NoErrorStackException;

public class ErrorUtil {
    /**
     * 抛出异常，不打印异常栈
     *
     * @param errorMsg
     */
    public static void throwNoErrorStack(String errorMsg) {
        throw new NoErrorStackException(errorMsg);
    }

    /**
     * 抛出参数错误异常
     *
     * @param errorMsg
     */
    public static void throwParameterError(String errorMsg) {
        throw new RuntimeException(errorMsg);
    }

    /**
     * 抛出第三方调用异常
     *
     * @param errorMsg
     */
    public static void throwInvokeError(String errorMsg) {
        throw new RuntimeException(errorMsg);
    }

    public static void throwError(ErrorCode errorCode) {
        throw new ApiParameterException(errorCode, null);
    }

    public static void notLoginError() {
        throw new ApiParameterException(ErrorCode.USER_ACCOUNT_NO_LOGIN, null);
    }
}
