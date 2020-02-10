package com.jike.certification.model.jurisdiction;

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
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@ApiModel("权限新增请求类")
public class JurisdictionInsertReq {
    @NotNull
    @ApiModelProperty(value = "系统id,不能为空")
    private Long thirdId;
    @NotNull
    @ApiModelProperty(value = "权限名,不能为空")
    private String name;
    @NotNull
    @ApiModelProperty(value = "系统解释")
    private String jurisdictionExplain;

}
