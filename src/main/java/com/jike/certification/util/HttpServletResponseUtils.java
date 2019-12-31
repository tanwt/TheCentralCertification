package com.jike.certification.util;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;

/**
 * @author wentong
 * @date 2019-12-05
 */
@Slf4j
public class HttpServletResponseUtils {

    public static void flushResponseToken(HttpServletResponse response, String token, Integer expiry){
        Cookie cookie = new Cookie("token", token);
        // 默认7天
        if (expiry == null) {
            expiry = 60 * 60 * 24 * 7;
        }
        cookie.setMaxAge(expiry);
        cookie.setPath("/");
        response.addCookie(cookie);
    }

    public static void deleteResponseToken(HttpServletRequest request, HttpServletResponse response){
        Cookie[] cookies = request.getCookies();
        Arrays.stream(cookies).forEach(cookie -> {
            if (cookie.getName().equals("token")) {
                cookie.setMaxAge(0);
                response.addCookie(cookie);
            }
        });
    }
}
