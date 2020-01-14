package com.jike.certification.model.third;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import org.springframework.beans.BeanUtils;

/**
 * @author wentong
 * @date 2020-01-13
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ApiModel(value = "第三方平台基础请求类")
public class ThirdBaseReq {
    @ApiModelProperty(value = "第三方平台id")
    private Long id;
    @ApiModelProperty(value = "第三方平台名")
    private String name;
}