package com.jike.certification.model.thirdRole;

import com.jike.certification.model.jurisdiction.JurisdictionVo;
import com.jike.certification.model.jurisdictionGroup.JurisdictionGroupListVo;
import com.jike.certification.model.jurisdictionGroup.JurisdictionGroupVo;
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
@ApiModel("角色权限组返回类")
public class RoleJurisdictionGroupVo {
    @ApiModelProperty(value = "权限组信息")
    private JurisdictionGroupListVo jurisdictionGroupVo;
    @ApiModelProperty(value = "权限组对应的权限信息")
    private List<RoleJurisdictionVo> roleJurisdictionVo;
}
