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
@ApiModel("权限更新请求类")
public class JurisdictionUpdateReq {
    @NotNull
    @ApiModelProperty(value = "权限id,不能为空")
    private Long id;
    @ApiModelProperty(value = "系统id,不能为空")
    private Long thirdId;
    @ApiModelProperty(value = "权限组id,选填")
    private Long jurisdictionGroupId;
    @ApiModelProperty(value = "权限名,选填")
    private String name;
    @ApiModelProperty(value = "权限解释,选填")
    private String jurisdictionExplain;
    @ApiModelProperty(value = "删除标示,0-正常 1-删除，选填")
    private Integer deleted;


}
