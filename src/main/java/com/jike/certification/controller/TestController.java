package com.jike.certification.controller;

import com.jike.certification.config.redis.RedisHandler;
import com.jike.certification.util.ContextUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author wentong
 * @date 2019-12-30
 */
@RestController
@RequestMapping("/api/pc/test")
public class TestController {

    @Autowired
    private RedisHandler redisHandler;

    @GetMapping("redis")
    public Object getRedis(){

        return ContextUtil.getUserToken();
    }
}
