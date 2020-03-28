package com.jike.certification.config;

import com.jike.certification.exception.ApiRuntimeException;
import com.jike.certification.model.Response;
import com.jike.certification.util.ResponseUtil;
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
        log.error("未知错误:{}", e.getMessage());
        log.error(e.getMessage(), e);
        return ResponseUtil.makeFail(e.getMessage());
    }

    @ExceptionHandler(value =RuntimeException.class)
    @ResponseBody
    public Response exceptionHandler(RuntimeException e){
        log.error("运行时异常:{}", e.getMessage());
        log.error(e.getMessage(), e);
        return ResponseUtil.makeFail(e.getMessage());
    }

    @ExceptionHandler(value =ApiRuntimeException.class)
    @ResponseBody
    public Response exceptionHandler(ApiRuntimeException e){
        log.error("逻辑错误:{}", e.getMessage());
        log.error(e.getMessage(), e);
        return ResponseUtil.makeFail(e.getErrorCode());
    }
}