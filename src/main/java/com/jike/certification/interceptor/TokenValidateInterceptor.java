package com.jike.certification.interceptor;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jike.certification.annotation.NoLoginRequired;
import com.jike.certification.exception.ApiParameterException;
import com.jike.certification.exception.ErrorCode;
import com.jike.certification.model.user.UserToken;
import com.jike.certification.service.UserTokenService;
import com.jike.certification.util.ContextUtil;
import com.jike.certification.util.ResponseUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Method;

/**
 * 登录验证
 *
 * @author wentong
 */
@Slf4j
public class TokenValidateInterceptor extends HandlerInterceptorAdapter {

    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private UserTokenService userTokenService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        ContextUtil.setRequest(request);
        ContextUtil.setResponse(response);
        if (!(handler instanceof HandlerMethod)) {
            return true;
        }
        String url = request.getRequestURI();
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        Method method = handlerMethod.getMethod();
        // 先清空ContextUtil中的用户和token信息
        ContextUtil.setUserId(null);
        ContextUtil.setUserToken(null);
        ContextUtil.setFromThirdId(null);
        // 用NoLoginRequired注解的方法，不需要登录。但下面会尝试解析token。
        // 微信端会在header 里面记录，而pc 在cookie ，所以都找一下
        boolean noLoginRequired = method.isAnnotationPresent(NoLoginRequired.class);
        String token = request.getHeader("token");
        if (StringUtils.isEmpty(token) && request.getCookies() != null) {
            Cookie[] cookies = request.getCookies();
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("token")) {
                    token = cookie.getValue();
                }
            }
        }
        if (StringUtils.isNotEmpty(token)) {
            // 不管怎样，都试图解析出userToken，如果不需要登录的话，解析失败也没关系
            try {
                UserToken userToken = userTokenService.getUserToken(token);
                ContextUtil.setUserId(userToken.getUserId());
                ContextUtil.setUserToken(userToken);
                ContextUtil.setFromThirdId(userToken.getThirdId());
                // 正常解析出userToken，返回正确
                // 刷新token 缓存时间
                userTokenService.setOrFlushToken(userToken);
                return true;
            } catch (Exception e) {
                e.printStackTrace();
                log.info("获取token失败, error={}", e.getMessage());
                // 解析userToken出错，但如果不需要登录的话，也不用管它
                if (noLoginRequired) {
                    return true;
                }
                // 需要登录，且token过期，返回token过期
                if (e instanceof ApiParameterException && ((ApiParameterException) e).getErrorCode() == ErrorCode.USER_ACCOUNT_TOKEN_EXPIRED) {
                    return checkFail(response, ErrorCode.USER_ACCOUNT_TOKEN_EXPIRED);
                }
                // 需要登录，未知错误，返回登录失败
                return checkFail(response, ErrorCode.USER_ACCOUNT_NO_LOGIN);
            }
        } else {
            // 没有token ，需要登陆
            return checkFail(response, ErrorCode.USER_ACCOUNT_NO_LOGIN);
        }
    }

    private boolean checkFail(HttpServletResponse response, ErrorCode errorCode) {
        try {
            response.setCharacterEncoding("utf-8");
            response.setContentType("application/json; charset=utf-8");
            response.getWriter().write(objectMapper.writeValueAsString(
                ResponseUtil.makeFail(errorCode)));
        } catch (IOException e) {
            log.info(e.getMessage(), e);
        }
        return false;
    }


}
