package com.jike.central.system1.controller;

/**
 * @author wentong
 * @date 2019-12-30
 */
@RestController
@RequestMapping("/api/pc/test")
public class TestController {


    @GetMapping("test")
    public String getRedis(){

        return "test";
    }
}
