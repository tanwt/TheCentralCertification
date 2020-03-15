package com.jike.certification.controller;

import com.jike.certification.model.roleJurisdictionRelevance.RoleJurisdictionRelevanceInsertReq;
import com.jike.certification.model.roleJurisdictionRelevance.RoleJurisdictionRelevanceVo;
import com.jike.certification.model.thirdRole.RoleJurisdictionVo;
import com.jike.certification.service.RoleJurisdictionRelevanceService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author wentong
 * @date 2020-03-07
 */
@RestController
@RequestMapping("/api/pc/roleJurisdictionRel")
@Api("角色权限关联相关接口")
public class RoleJurisdictionRelController {

    @Autowired
    private RoleJurisdictionRelevanceService relevanceService;

    @PostMapping("addRelevance")
    @ApiOperation("新增角色权限关联")
    public RoleJurisdictionRelevanceVo addRelevance(@RequestBody RoleJurisdictionRelevanceInsertReq insertReq){
        return relevanceService.getOrAddRel(insertReq);
    }

    @PostMapping("deleteRelevance")
    @ApiOperation("删除角色权限关联")
    public boolean deleteRelevance(Long relId){
        return relevanceService.deletedRel(relId);
    }
}
