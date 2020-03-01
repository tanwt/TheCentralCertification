package com.jike.certification.model.jurisdictionGroup;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

/**
 * @author wentong
 * @date 2020-02-08
 */
@Data
@Builder
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@ApiModel("权限返回类")
public class JurisdictionGroupVo {
    @ApiModelProperty(value = "权限组id")
    private Long id;
    @ApiModelProperty(value = "系统id")
    private Long thirdId;
    @ApiModelProperty(value = "权限组名")
    private String name;
    @ApiModelProperty(value = "系统组解释")
    private String typeExplain;
    @ApiModelProperty(value = "系统组更新时间")
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updateTime;

}
