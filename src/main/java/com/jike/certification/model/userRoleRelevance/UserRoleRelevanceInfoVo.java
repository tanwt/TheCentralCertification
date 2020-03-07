package com.jike.certification.model.userRoleRelevance;

import com.jike.certification.model.thirdRole.ThirdRoleVo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

/**
 * @author wentong
 * @date 2020-03-07
 */
@Data
@Builder
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@ApiModel("用户角色关联信息返回类")
public class UserRoleRelevanceInfoVo {
    @ApiModelProperty(value = "关联id")
    private Long relevanceId;
    @ApiModelProperty(value = "角色信息")
    private ThirdRoleVo thirdRoleVo;
    @ApiModelProperty("用户是否用户该角色")
    private boolean haveRole;
}
