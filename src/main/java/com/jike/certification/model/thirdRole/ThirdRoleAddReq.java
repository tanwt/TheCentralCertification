package com.jike.certification.model.thirdRole;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

/**
 * @author wentong
 * @date 2020-01-27
 */
@Data
@Builder
@ApiModel("三方角色新增请求类")
public class ThirdRoleAddReq {
    @NotNull
    @ApiModelProperty(value = "系统id，不能为空")
    private Long thirdId;
    @NotNull
    @ApiModelProperty(value = "角色名，不能为空")
    private String name;
}