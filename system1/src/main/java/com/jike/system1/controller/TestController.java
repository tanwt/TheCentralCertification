package com.jike.system1.controller;

import com.jike.system1.config.redis.RedisHandler;
import com.jike.system1.util.ContextUtil;
import com.jike.system1.util.HttpUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
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

    @PostMapping("test")
    public String test(){
        System.out.println("http test");
        return "http test";
    }

}
