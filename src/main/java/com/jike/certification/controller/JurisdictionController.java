package com.jike.certification.controller;

import com.jike.certification.model.Response;
import com.jike.certification.model.jurisdiction.JurisdictionInsertReq;
import com.jike.certification.service.JurisdictionService;
import com.jike.certification.util.ResponseUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author wentong
 * @date 2020-02-08
 */
@RestController
@RequestMapping("/api/pc/jurisdiction")
@Api("权限相关接口")
public class JurisdictionController {

    @Autowired
    private JurisdictionService jurisdictionService;


    @PostMapping("addJurisdiction")
    @ApiOperation(value = "新增权限")
    public Response<Boolean> addJurisdiction(@RequestBody @Validated JurisdictionInsertReq insertReq) {
        return ResponseUtil.makeSuccess(jurisdictionService.addJurisdiction(insertReq));
    }
}
