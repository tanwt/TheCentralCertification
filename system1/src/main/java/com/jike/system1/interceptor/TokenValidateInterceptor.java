package com.jike.system1.interceptor;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jike.system1.annotation.NoLoginRequired;
import com.jike.system1.exception.ApiParameterException;
import com.jike.system1.exception.ErrorCode;
import com.jike.system1.model.UserToken;
import com.jike.system1.service.UserTokenService;
import com.jike.system1.util.ContextUtil;
import com.jike.system1.util.HttpUtil;
import com.jike.system1.util.ResponseUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
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

//    private final static String URL = "http://120.79.94.90:8080/api/pc/user/checkUserToken?token=";
    private final static String URL = "http://localhost:8080/api/pc/user/checkUserToken?token=";

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
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
            // 到主系统判断token 是否有效
            String sendUrl = URL + token;
            String result = HttpUtil.sendGetRequest(sendUrl);
            JSONObject jsonObject = JSONObject.parseObject(result);
            boolean success = (Boolean)jsonObject.get("success");
            if (success){
                UserToken userToken = JSONObject.parseObject(jsonObject.get("data").toString(), UserToken.class);
                ContextUtil.setUserToken(userToken);
                return true;
            } else {
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
