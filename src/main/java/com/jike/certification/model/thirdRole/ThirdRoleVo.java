package com.jike.certification.model.thirdRole;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * @author wentong
 * @date 2020-01-27
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ApiModel("三方角色基础返回")
public class ThirdRoleVo {
    @ApiModelProperty(value = "角色id")
    private Long id;
    @ApiModelProperty(value = "系统id")
    private Long thirdId;
    @ApiModelProperty(value = "角色名")
    private String name;
    @ApiModelProperty(value = "角色创建时间")
    private LocalDateTime createTime;
}