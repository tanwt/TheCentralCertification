package com.jike.certification.interceptor;

import com.jike.certification.util.ContextUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 公共拦截器
 *
 * @author wentong
 */
@Slf4j
public class CommentInterceptor extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        ContextUtil.setRequest(request);
        ContextUtil.setResponse(response);
        response.setHeader("Access-Control-Allow-Origin", "*");
        return true;
    }

}
