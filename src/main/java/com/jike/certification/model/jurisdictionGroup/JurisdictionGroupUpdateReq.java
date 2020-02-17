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
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@ApiModel("权限组更新请求类")
public class JurisdictionGroupUpdateReq {
    @NotNull
    @ApiModelProperty(value = "权限组id,不能为空")
    private Long id;
    @NotNull
    @ApiModelProperty(value = "权限组所属系统,不能为空")
    private Long thirdId;
    @ApiModelProperty(value = "权限组名,选填")
    private String name;
    @ApiModelProperty(value = "系统组解释,选填")
    private String typeExplain;
    @ApiModelProperty(value = "删除标示,0-正常 1-删除，选传")
    private Integer deleted;

}
