package com.jike.certification.controller;

import com.jike.certification.model.Response;
import com.jike.certification.model.UserToken;
import com.jike.certification.model.user.UserLoginReq;
import com.jike.certification.model.user.UserPageReq;
import com.jike.certification.model.user.UserRegisterReq;
import com.jike.certification.model.user.UserVo;
import com.jike.certification.service.UserService;
import com.jike.certification.service.UserTokenService;
import com.jike.certification.util.ContextUtil;
import com.jike.certification.util.PageQueryResponse;
import com.jike.certification.util.ResponseUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**
 * @author wentong
 * @date 2019-12-26
 */
@RestController
@Slf4j
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

    @PostMapping("userPageList")
    @ApiOperation(value = "用户信息分页获取")
    public Response<PageQueryResponse<UserVo>> userPageList(@RequestBody UserPageReq userPageReq){
        log.info("用户登陆请求数据:{}", userPageReq);
        return ResponseUtil.makeSuccess(userService.userPageList(userPageReq));
    }

    @GetMapping("deletedUser")
    @ApiOperation(value = "删除用户")
    public Response<Boolean> deletedUser(Long userId){
        log.info("删除用户，操作人:{} 删除的用户id:{}", ContextUtil.getUserId(), userId);
        return ResponseUtil.makeSuccess(userService.deletedUser(userId));
    }
}
