package com.jike.certification.model.third;

import com.jike.certification.model.Pagination;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.validation.constraints.NotNull;

/**
 * @author wentong
 * @date 2020-01-13
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ApiModel(value = "第三方平台分页请求基础类")
public class ThirdPageReq {
    @ApiModelProperty(value = "第三方平台名字", notes = "模糊查询，选传")
    private String name;
    @ApiModelProperty(value = "分页数据", notes = "必传")
    @NotNull
    private Pagination pagination;
}
