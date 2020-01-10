package com.jike.system1.config;

import com.jike.system1.exception.ApiRuntimeException;
import com.jike.system1.model.Response;
import com.jike.system1.util.ResponseUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 全局异常捕获
 * @author wentong
 * @date 2019-12-27
 */
@ControllerAdvice
@Slf4j
public class MyExceptionHandler {

    @ExceptionHandler(value =Exception.class)
    @ResponseBody
    public Response exceptionHandler(Exception e){
        log.warn("未知错误:{}", e.getMessage());
        e.printStackTrace();
        return ResponseUtil.makeFail(e.getMessage());
    }

    @ExceptionHandler(value =RuntimeException.class)
    @ResponseBody
    public Response exceptionHandler(RuntimeException e){
        log.warn("运行时异常:{}", e.getMessage());
        e.printStackTrace();
        return ResponseUtil.makeFail(e.getMessage());
    }

    @ExceptionHandler(value = ApiRuntimeException.class)
    @ResponseBody
    public Response exceptionHandler(ApiRuntimeException e){
        log.warn("逻辑错误:{}", e.getMessage());
        e.printStackTrace();
        return ResponseUtil.makeFail(e.getErrorCode());
    }
}