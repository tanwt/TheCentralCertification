package com.jike.certification.controller;

import com.jike.certification.model.Response;
import com.jike.certification.model.jurisdictionGroup.JurisdictionGroupInsertReq;
import com.jike.certification.model.jurisdictionGroup.JurisdictionGroupListVo;
import com.jike.certification.model.jurisdictionGroup.JurisdictionGroupUpdateReq;
import com.jike.certification.service.JurisdictionGroupService;
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
@RequestMapping("/api/pc/jurisdictionGroup")
@Api("权限组相关接口")
public class JurisdictionGroupController {

    @Autowired
    private JurisdictionGroupService jurisdictionGroupService;


    @PostMapping("addJurisdictionGroup")
    @ApiOperation(value = "新增权限组")
    public Response<Boolean> addJurisdictionGroup(@RequestBody @Validated JurisdictionGroupInsertReq insertReq) {
        return ResponseUtil.makeSuccess(jurisdictionGroupService.addJurisdictionGroup(insertReq));
    }

    @PostMapping("updateJurisdictionGroup")
    @ApiOperation(value = "更新权限组")
    public Response<Boolean> updateJurisdictionGroup(@RequestBody @Validated JurisdictionGroupUpdateReq updateReq) {
        return ResponseUtil.makeSuccess(jurisdictionGroupService.updateJurisdictionGroup(updateReq));
    }

    @PostMapping("deletedGroup")
    @ApiOperation(value = "删除权限组")
    public Response<Boolean> deletedGroup(Long groupId) {
        return ResponseUtil.makeSuccess(jurisdictionGroupService.deletedGroup(groupId));
    }

    @GetMapping("getAllGroupByThirdId")
    @ApiOperation(value = "获取系统所有权限组")
    public Response<List<JurisdictionGroupListVo>> getAllGroupByThirdId(Long thirdId) {
        return ResponseUtil.makeSuccess(jurisdictionGroupService.getAllGroupByThirdId(thirdId));
    }
}
