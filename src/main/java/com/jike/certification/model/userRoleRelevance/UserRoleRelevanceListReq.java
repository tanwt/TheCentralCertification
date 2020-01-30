package com.jike.certification.model.userRoleRelevance;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @author wentong
 * @date 2020-01-30
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ApiModel(value = "用户角色关联分页查询类")
public class UserRoleRelevanceListReq {
    @NotNull
    @ApiModelProperty(value = "系统Id")
    private Long thirdId;
    @NotNull
    @ApiModelProperty(value = "角色Id")
    private Long roleId;
    @NotNull
    @ApiModelProperty(value = "用户Id 集合")
    private List<Long> userIdList;
}
