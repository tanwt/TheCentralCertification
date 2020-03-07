package com.jike.certification.model.thirdRole;

import com.jike.certification.model.jurisdictionGroup.JurisdictionGroupVo;
import com.jike.certification.model.userRoleRelevance.UserRoleRelevanceListVo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.util.List;

/**
 * @author wentong
 * @date 2020-02-08
 */
@Data
@Builder
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@ApiModel("角色信息返回类")
public class ThirdRoleInfoVo {
    @ApiModelProperty(value = "角色信息")
    private ThirdRoleVo thirdRoleVo;
    @ApiModelProperty(value = "用户角色关联信息")
    private List<UserRoleRelevanceListVo> userRoleRelevanceList;
    @ApiModelProperty(value = "角色对应的权限信息")
    private List<RoleJurisdictionGroupVo> roleJurisdictionGroupVoList;
}
