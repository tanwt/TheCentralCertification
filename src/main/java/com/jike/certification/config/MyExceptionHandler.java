package com.jike.certification.config;

import com.jike.certification.exception.ApiRuntimeException;
import com.jike.certification.model.Response;
import com.jike.certification.util.ResponseUtil;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 全局异常捕获
 * @author wentong
 * @date 2019-12-27
 */
@ControllerAdvice
public class MyExceptionHandler {

    @ExceptionHandler(value =Exception.class)
    @ResponseBody
    public Response exceptionHandler(Exception e){
        return ResponseUtil.makeFail(e.getMessage());
    }

    @ExceptionHandler(value =RuntimeException.class)
    @ResponseBody
    public Response exceptionHandler(RuntimeException e){
        return ResponseUtil.makeFail(e.getMessage());
    }

    @ExceptionHandler(value =ApiRuntimeException.class)
    @ResponseBody
    public Response exceptionHandler(ApiRuntimeException e){
        return ResponseUtil.makeFail(e.getErrorCode());
    }
}