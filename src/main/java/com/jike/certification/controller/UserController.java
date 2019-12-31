package com.jike.certification.controller;

import com.jike.certification.model.Response;
import com.jike.certification.model.user.UserLoginReq;
import com.jike.certification.model.user.UserRegisterReq;
import com.jike.certification.model.user.UserToken;
import com.jike.certification.model.user.UserVO;
import com.jike.certification.service.UserService;
import com.jike.certification.service.UserTokenService;
import com.jike.certification.util.ResponseUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

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
    @Autowired
    private UserTokenService userTokenService;

    @PostMapping("register")
    @ApiOperation(value = "用户注册")
    public Response<Long> register(@Valid @RequestBody UserRegisterReq userRegisterReq){
        return ResponseUtil.makeSuccess(userService.userRegister(userRegisterReq));
    }

    @PostMapping("loginByMail")
    @ApiOperation(value = "用户通过邮箱登陆")
    public Response<UserToken> loginByMail(@Valid @RequestBody UserLoginReq userLoginReq){
        return ResponseUtil.makeSuccess(userService.userLoginByMail(userLoginReq));
    }

    @GetMapping("checkUserToken")
    @ApiOperation(value = "检查用户token")
    public Response<UserToken> checkUserToken(@NotNull String token){
        return ResponseUtil.makeSuccess(userTokenService.getUserToken(token));
    }
}
