package com.jike.certification.model.jurisdiction;

import com.jike.certification.model.jurisdictionGroup.JurisdictionGroupVo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

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
@ApiModel("权限列表请求类")
public class JurisdictionListReq {
    @ApiModelProperty(value = "系统id,选传")
    private Long thirdId;
    @ApiModelProperty(value = "权限组id,选传")
    private Long jurisdictionGroupId;
}
