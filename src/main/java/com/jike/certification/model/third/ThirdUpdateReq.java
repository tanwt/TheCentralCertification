package com.jike.certification.model.third;

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
@ApiModel(value = "第三方平台更新请求类")
public class ThirdUpdateReq {
    @ApiModelProperty(value = "第三方平台id，必传")
    @NotNull
    private Long id;
    @ApiModelProperty(value = "第三方平台名，选传")
    private String name;
    @ApiModelProperty(value = "是否删除（0 正常 1 删除），选传")
    private Integer deleted;
}