package com.jike.certification.model.jurisdictionGroup;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.validation.constraints.NotNull;

/**
 * @author wentong
 * @date 2020-02-08
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ApiModel("权限组新增请求类")
public class JurisdictionGroupInsertReq {
    @NotNull
    @ApiModelProperty(value = "系统id,不能为空")
    private Long thirdId;
    @NotNull
    @ApiModelProperty(value = "权限组名,不能为空")
    private String name;
    @ApiModelProperty(value = "系统组解释,选填")
    private String typeExplain;

}
