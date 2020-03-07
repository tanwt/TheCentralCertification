package com.jike.certification.model.user;

import com.jike.certification.model.Pagination;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.validation.constraints.NotNull;

/**
 * @author wentong
 * @date 2019-12-26
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ApiModel(value = "用户分页请求类")
public class UserPageReq {
    @ApiModelProperty(value = "用户名")
    private String userName;
    @ApiModelProperty(value = "分页数据,必传")
    private Pagination pagination;
    @ApiModelProperty(value = "创建时间排序条件,默认倒序-desc,正序-asc")
    private String createTimeOrder;
}
