package com.jike.certification.interceptor;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jike.certification.annotation.NoLoginRequired;
import com.jike.certification.biz.JurisdictionBiz;
import com.jike.certification.biz.RoleJurisdictionRelevanceBiz;
import com.jike.certification.biz.ThirdRoleBiz;
import com.jike.certification.biz.UserRoleRelevanceBiz;
import com.jike.certification.exception.ApiParameterException;
import com.jike.certification.exception.ErrorCode;
import com.jike.certification.model.UserToken;
import com.jike.certification.model.jurisdiction.Jurisdiction;
import com.jike.certification.model.roleJurisdictionRelevance.RoleJurisdictionRelevance;
import com.jike.certification.model.userRoleRelevance.UserRoleRelevance;
import com.jike.certification.service.UserJurisdictionRelevanceService;
import com.jike.certification.service.UserTokenService;
import com.jike.certification.util.CollectionUtil;
import com.jike.certification.util.ContextUtil;
import com.jike.certification.util.ResponseUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.List;

/**
 * @author wentong
 * @date 2020-05-31
 */
@Slf4j
public class JurisdictionValidateInterceptor extends HandlerInterceptorAdapter {

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private UserJurisdictionRelevanceService userJurisdictionRelevanceService;

    /**
     * 检查用户是否拥有该路径的访问权限
     * @param request
     * @param response
     * @param handler
     * @return
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        String requestURI = request.getRequestURI();
        boolean checkUserJurisdiction = userJurisdictionRelevanceService.checkUserJurisdiction(ContextUtil.getUserId(), requestURI);
        if (!checkUserJurisdiction) {
            return checkFail(response, ErrorCode.USER_ACCOUNT_PERMISSION_ERROR);
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
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
