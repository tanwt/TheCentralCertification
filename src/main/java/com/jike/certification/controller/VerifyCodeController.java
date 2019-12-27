package com.jike.certification.controller;

import com.jike.certification.model.Response;
import com.jike.certification.service.VerifyCodeService;
import com.jike.certification.util.ResponseUtil;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author wentong
 * @date 2019-12-27
 */
@RestController
@RequestMapping(path = "/api/pc/verifyCode")
@Api(value = "验证PC 端相关接口")
public class VerifyCodeController {

    @Autowired
    private VerifyCodeService verifyCodeService;

    @GetMapping("sendVerifyCode")
    public Response<Long> sendVerifyCode(String mail){
        return ResponseUtil.makeSuccess(verifyCodeService.sendVerifyCodeByMail(mail));
    }
}
