package com.jike.certification.controller;

import com.jike.certification.commentEnum.DeleteStatus;
import com.jike.certification.model.Response;
import com.jike.certification.model.third.*;
import com.jike.certification.service.ThirdService;
import com.jike.certification.util.MyBeanUtils;
import com.jike.certification.util.PageQueryResponse;
import com.jike.certification.util.ResponseUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * @author wentong
 * @date 2020-01-14
 */
@RestController
@RequestMapping("/api/pc/third")
@Api(value = "第三方接口")
public class ThirdController {

    @Autowired
    private ThirdService thirdService;

    @PostMapping("addThird")
    @ApiOperation(value = "新增第三方系统")
    public Response<ThirdVo> addThird(@Valid @RequestBody ThirdAddReq thirdAddReq) {
        return ResponseUtil.makeSuccess(thirdService.addThird(thirdAddReq));
    }

    @PostMapping("updateThird")
    @ApiOperation(value = "更新第三方系统")
    public Response<ThirdVo> updateThird(@Valid @RequestBody ThirdUpdateReq thirdUpdateReq) {
        return ResponseUtil.makeSuccess(thirdService.updateThird(MyBeanUtils.myCopyProperties(thirdUpdateReq, new Third())));
    }

    @GetMapping("deletedThird")
    @ApiOperation(value = "删除第三方系统")
    public Response<ThirdVo> deletedThird(Long thirdId) {
        ThirdUpdateReq thirdUpdateReq = ThirdUpdateReq.builder()
                                            .id(thirdId)
                                            .deleted(DeleteStatus.DELETED.getValue())
                                            .build();
        return ResponseUtil.makeSuccess(thirdService.updateThird(MyBeanUtils.myCopyProperties(thirdUpdateReq, new Third())));
    }

    @PostMapping("thirdList")
    @ApiOperation(value = "第三方系统分页获取")
    public Response<PageQueryResponse<ThirdPageVo>> thirdList(@Valid @RequestBody ThirdPageReq thirdPageReq) {
        return ResponseUtil.makeSuccess(thirdService.thirdList(thirdPageReq));
    }

    @PostMapping("getUserThirdInfo")
    @ApiOperation(value = "获取用户在各个系统的信息")
    public Response<List<UserThirdInfoVo>> getUserThirdInfo(Long userId) {
        return ResponseUtil.makeSuccess(thirdService.getUserThirdInfo(userId));
    }

    @PostMapping("queryAllThird")
    @ApiOperation(value = "获取所有第三方系统")
    public Response<List<ThirdVo>> queryAllThird() {
        return ResponseUtil.makeSuccess(thirdService.queryAllThird());
    }


}
