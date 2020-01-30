package com.jike.certification.controller;

import com.jike.certification.model.Response;
import com.jike.certification.model.thirdRole.ThirdRoleAddReq;
import com.jike.certification.model.thirdRole.ThirdRoleUpdateReq;
import com.jike.certification.model.thirdRole.ThirdRoleVo;
import com.jike.certification.service.ThirdRoleService;
import com.jike.certification.util.ResponseUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author wentong
 * @date 2020-01-28
 */
@RestController
@Api(value = "三方系统角色相关接口")
@RequestMapping("/api/pc/thirdRole")
public class ThirdRoleController {

    @Autowired
    private ThirdRoleService thirdRoleService;

    @ApiOperation(value = "新增角色")
    @PostMapping("addThirdRole")
    public Response<ThirdRoleVo> addThirdRole(@Validated @RequestBody ThirdRoleAddReq thirdRoleAddReq){
        ThirdRoleVo thirdRoleVo = thirdRoleService.addRole(thirdRoleAddReq);
        return ResponseUtil.makeSuccess(thirdRoleVo);
    }

    @ApiOperation(value = "更新角色")
    @PostMapping("updateRole")
    public Response<Boolean> updateRole(@Validated @RequestBody ThirdRoleUpdateReq thirdRoleUpdateReq){
        return ResponseUtil.makeSuccess(thirdRoleService.updateRole(thirdRoleUpdateReq));
    }

    @ApiOperation(value = "删除角色")
    @PostMapping("deletedRole")
    public Response<Boolean> deletedRole(Long roleId){
        return ResponseUtil.makeSuccess(thirdRoleService.deletedRole(roleId));
    }

    @ApiOperation(value = "查询一个系统中的角色")
    @GetMapping("selectByThirdId")
    public Response<List<ThirdRoleVo>> selectByThirdId(Long thirdId){
        List<ThirdRoleVo> thirdRoleVos = thirdRoleService.selectByThirdId(thirdId);
        return ResponseUtil.makeSuccess(thirdRoleVos);
    }
}
