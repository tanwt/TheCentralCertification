package com.jike.certification.model.roleJurisdictionRelevance;

import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

/**
 * @author wentong
 * @date 2020-02-11
 */
@Data
@Builder
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@ApiModel("用户权限新增请求")
public class RoleJurisdictionRelevanceInsertReq extends Model<RoleJurisdictionRelevanceInsertReq> {
    @ApiModelProperty("用户权限关联角色id")
    private Long roleId;
    @ApiModelProperty("用户权限权限id")
    private Long jurisdictionId;
}
