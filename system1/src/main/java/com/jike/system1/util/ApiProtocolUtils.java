package com.jike.system1.util;

import com.google.common.collect.Maps;

import javax.servlet.http.HttpServletRequest;
import java.util.Collections;
import java.util.Map;

public class ApiProtocolUtils {
    /**
     * 获取API的信息
     */
    public static Map<String, String> getApiInfo(HttpServletRequest request) {
        String path = request.getServletPath();
        if (path == null || path.equals("/")) {
            return Collections.emptyMap();
        }
        Map<String, String> results = Maps.newHashMap();
        int versionOf = path.indexOf("/", 1);
        if (versionOf == -1) {
            return Collections.emptyMap();
        }
        String version = path.substring(1, versionOf);
        String api = "/" + path.substring(path.indexOf("/", 1) + 1);
        results.put("version", version);
        results.put("api", api);
        return results;
    }

    public static String parameterMapToLog(HttpServletRequest request) {
        Map<String, String[]> params = request.getParameterMap();
        if (params == null || params.size() == 0) {
            return "parameter: null ";
        }
        StringBuilder s = new StringBuilder("parameter: ");
        for (String key : params.keySet()) {
            s.append(key).append("=");
            Object value = params.get(key);
            if (value != null) {
                String[] arr = (String[]) value;
                if (arr.length == 1) {
                    s.append(arr[0]).append(";");
                } else {
                    s.append("string[]").append(";");
                }
            }
        }
        s.append(" ");
        return s.toString();
    }

    public static String getDeviceFromUserAgent(String userAgent) {
        if (userAgent == null || userAgent.length() == 0) {
            return "";
        }
        if (!userAgent.contains("[") && !userAgent.contains("]")) {
            return "";
        }
        return userAgent.substring(userAgent.indexOf("[") + 1, userAgent.lastIndexOf("]"));
    }
}

