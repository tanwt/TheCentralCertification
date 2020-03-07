package com.jike.certification.model.jurisdictionGroup;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.jike.certification.model.jurisdiction.JurisdictionVo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author wentong
 * @date 2020-02-08
 */
@Data
@Builder
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@ApiModel("权限组及其权限返回类")
public class GroupWithJurisdictionVo {
    @ApiModelProperty(value = "权限组信息")
    private JurisdictionGroupVo jurisdictionGroupVo;
    @ApiModelProperty(value = "权限组下的权限信息")
    private List<JurisdictionVo> jurisdictionVoList;
}
