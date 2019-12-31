package com.jike.certification.service;

import com.alibaba.fastjson.JSONObject;
import com.jike.certification.biz.UserTokenBiz;
import com.jike.certification.config.redis.RedisHandler;
import com.jike.certification.exception.ApiParameterException;
import com.jike.certification.exception.ErrorCode;
import com.jike.certification.model.user.UserToken;
import com.jike.certification.util.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Type;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;


/**
 * @author wentong
 */
@Slf4j
@Service
public class UserTokenService {
    @Autowired
    private UserTokenBiz userTokenBiz;

    @Autowired
    private RedisHandler redisHandler;


    /**
     * 设置或更新userToken
     * <p>
     * token 7 天失效
     *
     * @param userToken
     * @return
     */
    public UserToken setOrFlushToken(UserToken userToken) {
        MyAssert.notNull(userToken, "token 为空");
        MyAssert.notNull(userToken.getUserId(), "userId 不能为空");
        boolean newToken = true;
        UserToken oldUserToken = userTokenBiz.queryByUserId(userToken.getUserId());
        // 存在token 就用旧的
        if (oldUserToken != null) {
            newToken = false;
            BeanUtils.copyProperties(oldUserToken, userToken);
        } else {
            setToken(userToken);
        }
        setExpireTime(userToken);
        storeUserToken(userToken, newToken);
        return userToken;
    }

    /**
     * 获取用户缓存
     *
     * @param token
     * @return
     */
    public UserToken getUserToken(String token) {
        String key = tokenKey(token);
        UserToken userToken = redisHandler.get(key, UserToken.class);
        if (userToken == null) {
            ErrorUtil.notLoginError();
        }
        validateUserId(userToken);
        if (userToken.getExpireTime().isBefore(LocalDateTime.now())) {
            throw new ApiParameterException(ErrorCode.USER_ACCOUNT_TOKEN_EXPIRED, null);
        }
        return userToken;
    }

    public void storeUserToken(UserToken userToken, boolean newToken) {
        if (newToken) {
            userTokenBiz.save(userToken);
        } else {
            userTokenBiz.update(userToken);
        }
        redisHandler.expire(tokenKey(userToken.getToken()), userToken);
        ContextUtil.setUserToken(userToken);
        ContextUtil.setFromThirdId(userToken.getThirdId());
        // 在返回里面增加cookie
        HttpServletResponseUtils.flushResponseToken((HttpServletResponse) ContextUtil.getResponse(), userToken.getToken(), null);
    }

    /**
     * 设置token
     *
     * @param userToken
     */
    private void setToken(UserToken userToken) {
        String token = EncryptUtil.aesEncryptUrlSafe(userToken.getUserId() + "-" + UUIDUtils.createUuid().substring(10));
        userToken.setToken(token);
    }

    /**
     * 设置过期时间
     *
     * @param userToken
     */
    private void setExpireTime(UserToken userToken) {
        userToken.setExpireTime(LocalDateTime.now().plusDays(7));
    }

    private String tokenKey(String token) {
        return "UserToken_" + token;
    }

    private void validateUserId(UserToken userToken) {
        String token = userToken.getToken();
        String decryptToken = EncryptUtil.aesDecryptUrlSafe(token);
        String[] strings = decryptToken.split("-");
        if (!userToken.getUserId().equals(Long.valueOf(strings[0]))) {
            log.error("userId不相等, userToken.userId={}, userId from token={}", userToken.getUserId(), strings[0]);
            ErrorUtil.notLoginError();
        }
    }

}
