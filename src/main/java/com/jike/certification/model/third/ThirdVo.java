package com.jike.certification.model.third;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

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
@ApiModel(value = "第三方平台基础返回类")
public class ThirdVo {
    @ApiModelProperty(value = "第三方平台id")
    private Long id;
    @ApiModelProperty(value = "第三方平台名")
    private String name;
    @ApiModelProperty(value = "第三方平台说明")
    private String thirdExplain;
    @ApiModelProperty(value = "第三方平台创立时间")
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;
}
