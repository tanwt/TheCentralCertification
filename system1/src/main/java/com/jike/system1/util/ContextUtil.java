package com.jike.system1.util;

import com.jike.system1.constants.ContextFieldCons;
import com.jike.system1.exception.ErrorCode;
import com.jike.system1.model.UserToken;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.math.NumberUtils;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.util.HashMap;

/**
 * 线程变量工具类
 * @author wentong
 */
@Slf4j
public class ContextUtil {

    private static Long DEFAULT_USER_ID = 3716L;

    private static ThreadLocal<HashMap<String, Object>> envStore = new ThreadLocal<HashMap<String, Object>>() {
        @Override
        protected HashMap<String, Object> initialValue() {
            return new HashMap<String, Object>();
        }
    };

    /**
     * 清空线程变量,在程序的AOP中调用
     */
    public static void clear() {
        envStore.get().clear();
    }

    public static void remove(String contextKey) {
        envStore.get().remove(contextKey);
    }

    public static void setRequest(ServletRequest servletRequest) {
        envStore.get().put(ContextFieldCons.KEY_REQUEST, servletRequest);
    }

    public static ServletRequest getRequest() {
        return (ServletRequest) envStore.get().get(ContextFieldCons.KEY_REQUEST);
    }

    public static void setResponse(ServletResponse servletResponse) {
        envStore.get().put(ContextFieldCons.KEY_RESPONSE, servletResponse);
    }

    public static ServletResponse getResponse() {
        return (ServletResponse) envStore.get().get(ContextFieldCons.KEY_RESPONSE);
    }

    public static void setOSType(Integer osType) {
        envStore.get().put(ContextFieldCons.KEY_OS_TYPE, osType);
    }

    public static Integer getOSType() {
        return (Integer) envStore.get().get(ContextFieldCons.KEY_OS_TYPE);
    }

    public static void setChannel(String channel) {
        envStore.get().put(ContextFieldCons.KEY_CHANNEL, channel);
    }

    public static String getChannel() {
        return (String) envStore.get().get(ContextFieldCons.KEY_CHANNEL);
    }

    public static void setClientIp(String ip) {
        envStore.get().put(ContextFieldCons.KEY_CLIENT_IP, ip);
    }

    public static String getClientIp() {
        return (String) envStore.get().get(ContextFieldCons.KEY_CLIENT_IP);
    }

    public static void setUserAgent(String userAgent) {
        envStore.get().put(ContextFieldCons.KEY_USER_AGENT, userAgent);
    }

    public static String getUserAgent() {
        return (String) envStore.get().get(ContextFieldCons.KEY_USER_AGENT);
    }

    public static String getDeviceId() {
        return (String) envStore.get().get(ContextFieldCons.KEY_DEVICE_ID);
    }

    public static void setDeviceId(String deviceId) {
        envStore.get().put(ContextFieldCons.KEY_DEVICE_ID, deviceId);
    }

    public static void setUserId(Long userId) {
        log.debug("setUserId:{}", userId);
        envStore.get().put(ContextFieldCons.KEY_USER_ID, userId);
    }

    public static Long getUserId() {
        return (Long) envStore.get().get(ContextFieldCons.KEY_USER_ID);
    }

    public static Long getUserIdOrDefault() {
        Long userId = (Long) envStore.get().get(ContextFieldCons.KEY_USER_ID);
        if (userId == null) {
            userId = DEFAULT_USER_ID;
        }
        return userId;
    }

    public static Long getDefaultUserId() {
        return DEFAULT_USER_ID;
    }

    public static Long getUserIdNotNull() {
        Long userId = (Long) envStore.get().get(ContextFieldCons.KEY_USER_ID);
        MyAssert.notNull(userId, ErrorCode.USER_ACCOUNT_NO_LOGIN);
        return userId;
    }

    public static void setUserToken(UserToken token) {
        log.debug("setUserToken:{}", token);
        envStore.get().put(ContextFieldCons.USER_TOKEN, token);
    }

    public static UserToken getUserToken() {
        return (UserToken) envStore.get().get(ContextFieldCons.USER_TOKEN);
    }

    public static UserToken getUserTokenWithCheck() {
        UserToken token = (UserToken) envStore.get().get(ContextFieldCons.USER_TOKEN);
        MyAssert.notNull(token, ErrorCode.USER_ACCOUNT_NO_LOGIN);
        return token;
    }
    public static void removeUserId() {
        remove(ContextFieldCons.KEY_USER_ID);
    }

    public static void setUniqId(String uniqId) {
        envStore.get().put(ContextFieldCons.KEY_UNIQ_ID, uniqId);
    }

    public static String getUniqId() {
        return (String) envStore.get().get(ContextFieldCons.KEY_UNIQ_ID);
    }

    public static void setAppVersion(String appVersion) {
        envStore.get().put(ContextFieldCons.KEY_APP_VERSION, appVersion);
    }

    public static String getAppVersion() {
        return (String) envStore.get().get(ContextFieldCons.KEY_APP_VERSION);
    }

    public static void setFromThirdId(Integer thirdId) {
        envStore.get().put(ContextFieldCons.KEY_THIRD_ID, thirdId);
    }

    public static int getFromThirdId() {
        return NumberUtils.toInt(String.valueOf(envStore.get().get(ContextFieldCons.KEY_THIRD_ID)));
    }

    public static void setReferer(String referer) {
        envStore.get().put(ContextFieldCons.KEY_REFERER, referer);
    }

    public static String getReferer() {
        return (String) envStore.get().get(ContextFieldCons.KEY_REFERER);
    }

    public static void setSelfTag(String selfTag) {
        envStore.get().put(ContextFieldCons.KEY_SELF_TAG, selfTag);
    }

    public static String getSelfTag() {
        return (String) envStore.get().get(ContextFieldCons.KEY_SELF_TAG);
    }

}
