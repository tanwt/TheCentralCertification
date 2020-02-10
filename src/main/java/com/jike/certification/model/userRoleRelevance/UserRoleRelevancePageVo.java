package com.jike.certification.model.userRoleRelevance;

import com.jike.certification.model.user.UserVo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

/**
 * @author wentong
 * @date 2020-01-30
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ApiModel(value = "用户角色关联分页返回类")
public class UserRoleRelevancePageVo {
    @ApiModelProperty(value = "关联id")
    private Long id;
    @ApiModelProperty(value = "系统Id")
    private Long thirdId;
    @ApiModelProperty(value = "角色Id")
    private Long roleId;
    @ApiModelProperty(value = "用户信息")
    private UserVo userVo;
}
