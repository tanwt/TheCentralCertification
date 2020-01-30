package com.jike.certification.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jike.certification.factory.WrapperFactory;
import com.jike.certification.model.Response;
import com.jike.certification.model.userRoleRelevance.UserRoleRelevance;
import com.jike.certification.model.userRoleRelevance.UserRoleRelevanceBatchInsert;
import com.jike.certification.service.UserRoleRelevanceService;
import com.jike.certification.util.ResponseUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author wentong
 * @date 2020-01-30
 */
@RestController
@RequestMapping("api/pc/userRoleRelevance")
@Api("用户角色关联相关接口")
public class UserRoleRelevanceController {

    @Autowired
    private UserRoleRelevanceService userRoleRelevanceService;

    @PostMapping("insertBatch")
    @ApiOperation(value = "批量新增关联")
    public Response<Boolean> insertBatch(@RequestBody @Validated UserRoleRelevanceBatchInsert userRoleRelevanceBatchInsert) {
        return ResponseUtil.makeSuccess(userRoleRelevanceService.insertBatchToThirdRole(userRoleRelevanceBatchInsert.getThirdId()
            , userRoleRelevanceBatchInsert.getRoleId()
            , userRoleRelevanceBatchInsert.getUserIdList()));
    }

    @PostMapping("deletedBatch")
    @ApiOperation(value = "批量删除关联")
    public Response<Boolean> deletedBatch(@RequestBody List<Long> idList) {
        return ResponseUtil.makeSuccess(userRoleRelevanceService.deletedBatch(idList));
    }
}
