package com.jike.certification.controller;

import com.jike.certification.config.redis.RedisHandler;
import com.jike.certification.util.ContextUtil;
import com.sun.deploy.net.HttpResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpRequest;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

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

    @PostMapping("test1")
    public String test1() throws InterruptedException {
//        Thread.sleep(400L);
        System.out.println("test1");
        return "test1";
    }

    @GetMapping("test2")
    public void test2(HttpServletResponse response, @RequestParam("code") Integer code){
        System.out.println("code: " + code);
        response.setStatus(code);
    }
}
