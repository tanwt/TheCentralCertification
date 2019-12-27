package com.jike.certification.constants;

public final class CommonConstants {

    private CommonConstants() {
    }

    public static final String CHARSET = "UTF-8";

    public static final String DEFAULT_ANCHOR_ID = "0";

    public static final String DEFAULT_PAGE_SIZE = "20";

    public static final int MAX_PAGE_SIZE = 200;

    public static final String STANDARD_DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";

    public static final String COMPACT_DATE_FORMAT = "yyyyMMdd";

    public static final String WX_GET_WXA_CODE_URL = "https://controller.weixin.qq.com/wxa/getwxacode?access_token=%s";

    public static final String WX_GET_WXA_CODE_UMLIMIT_URL = "https://controller.weixin.qq.com/wxa/getwxacodeunlimit?access_token=%s";

    public static final String WX_CODE_DIR = "/core/wx/";

    public static final Long COS_SIGN_EXPIRE_TIME = 1000L * 60 * 60;

    // hash在redis的TTL
    public static final long HASH_REDIS_TTL = 1000L * 60 * 60 * 24 * 7;

    // 对象在redis的TTL，单位为毫秒，默认缓存时间为3天
    public static final long OBJECT_REDIS_TTL = 1000L * 60 * 60 * 24 * 3;

    // 只存储一天的对象TTL
    public static final long DAILY_REDIS_TTL = 1000L * 60 * 60 * 24;

    // 别名map在redis的TTL
    public static final long ALIAS_MAP_REDIS_TTL = 1000L * 60 * 60 * 24 * 7;

    // DRM token的TTL，24小时
    public static final long DRM_TOKEN_REDIS_TTL = 1000L * 60 * 60 * 24;

    // grafana指标：缓存请求数
    public static final String METRICS_NAME_CACHE_GET = "redis_cache_get";

    // grafana指标：缓存未请求数
    public static final String METRICS_NAME_CACHE_MISS = "redis_cache_miss";

    public static final class Lock {

        public static final long WAIT_TIME = 5000L;

        public static final long LEASE_TIME = 3000L;
    }
}