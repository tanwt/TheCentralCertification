package com.jike.certification.controller;

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
    public Response<ThirdVo> addThird(@Valid @RequestBody ThirdBaseReq thirdBaseReq) {
        return ResponseUtil.makeSuccess(thirdService.addThird(thirdBaseReq));
    }

    @PostMapping("updateThird")
    @ApiOperation(value = "更新第三方系统")
    public Response<Integer> updateThird(@Valid @RequestBody ThirdUpdateReq thirdUpdateReq) {
        return ResponseUtil.makeSuccess(thirdService.updateThird(MyBeanUtils.myCopyProperties(thirdUpdateReq, new Third())));
    }

    @GetMapping("thirdList")
    @ApiOperation(value = "第三方系统分页获取")
    public Response<PageQueryResponse<ThirdListVo>> thirdList(@Valid @RequestBody ThirdListReq thirdListReq) {
        return ResponseUtil.makeSuccess(thirdService.thirdList(thirdListReq));
    }




}
