package com.jike.certification.controller;

import com.jike.certification.model.Response;
import com.jike.certification.model.user.UserRegisterReq;
import com.jike.certification.service.UserService;
import com.jike.certification.util.ResponseUtil;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * @author wentong
 * @date 2019-12-26
 */
@RestController
@RequestMapping(path = "api/pc/user")
@Api(value = "用户PC 端相关接口")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("register")
    public Response<Long> register(@Valid @RequestBody UserRegisterReq userRegisterReq){
        return ResponseUtil.makeSuccess(userService.userRegister(userRegisterReq));
    }
}
