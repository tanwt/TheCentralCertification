package com.jike.certification.controller;

import com.jike.certification.model.Response;
import com.jike.certification.model.jurisdiction.*;
import com.jike.certification.service.JurisdictionService;
import com.jike.certification.util.ResponseUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public Response<JurisdictionVo> addJurisdiction(@RequestBody JurisdictionInsertReq insertReq) {
        return ResponseUtil.makeSuccess(jurisdictionService.addJurisdiction(insertReq));
    }

    @PostMapping("updateJurisdiction")
    @ApiOperation(value = "更新权限")
    public Response<JurisdictionVo> updateJurisdiction(@RequestBody @Validated JurisdictionUpdateReq updateReq) {
        return ResponseUtil.makeSuccess(jurisdictionService.updateJurisdiction(updateReq));
    }

    @GetMapping("queryJurisdictionList")
    @ApiOperation(value = "获取权限列表")
    public Response<List<JurisdictionListVo>> queryJurisdictionList(@RequestBody @Validated JurisdictionListReq listReq) {
        return ResponseUtil.makeSuccess(jurisdictionService.queryJurisdictionList(listReq));
    }
}
