package com.jike.certification.model.third;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import org.springframework.beans.BeanUtils;

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
@ApiModel(value = "第三方平台新增请求类")
public class ThirdAddReq {
    @ApiModelProperty(value = "第三方平台名，必传")
    @NotNull
    private String name;
    @ApiModelProperty(value = "第三方平台说明，选传")
    private String thirdExplain;
}