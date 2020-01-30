package com.jike.certification.model.thirdRole;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @author wentong
 * @date 2020-01-27
 */
@Data
@Builder
@ApiModel("三方角色更新请求类")
public class ThirdRoleUpdateReq {
    @NotNull
    @ApiModelProperty(value = "角色id,不能为空")
    private Long id;
    @ApiModelProperty(value = "系统id")
    private Long thirdId;
    @ApiModelProperty(value = "角色名")
    private String name;
}