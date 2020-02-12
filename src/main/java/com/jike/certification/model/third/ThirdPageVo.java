package com.jike.certification.model.third;

import com.jike.certification.model.Pagination;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.time.LocalDateTime;

/**
 * @author wentong
 * @date 2020-01-13
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ApiModel(value = "第三方平台分页返回基础类")
public class ThirdPageVo {
    @ApiModelProperty(value = "第三方平台id")
    private Long id;
    @ApiModelProperty(value = "第三方平台名字")
    private String name;
    @ApiModelProperty(value = "第三方平台说明")
    private String thirdExplain;
    @ApiModelProperty(value = "第三方平台更新时间")
    private LocalDateTime updateTime;
}
