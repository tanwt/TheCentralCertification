package com.jike.certification.model.userRoleRelevance;

import com.jike.certification.model.Pagination;
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
public class UserRoleRelevancePageReq {
    @ApiModelProperty(value = "系统Id")
    private Long thirdId;
    @ApiModelProperty(value = "角色Id")
    private Long roleId;
    @ApiModelProperty(value = "用户Id")
    private List<Long> userIdList;
    @NotNull
    @ApiModelProperty(value = "分页数据, 不能为空")
    private Pagination pagination;
}
