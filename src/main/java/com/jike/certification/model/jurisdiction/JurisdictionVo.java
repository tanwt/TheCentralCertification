package com.jike.certification.model.jurisdiction;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.jike.certification.model.jurisdictionGroup.JurisdictionGroupVo;
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
public class JurisdictionVo {
    @ApiModelProperty(value = "权限id")
    private Long id;
    @ApiModelProperty(value = "系统id")
    private Long thirdId;
    @ApiModelProperty(value = "权限名")
    private String name;
    @ApiModelProperty(value = "权限解释")
    private String jurisdictionExplain;
    @ApiModelProperty(value = "删除标示,0-正常 1-删除")
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updateTime;

}
