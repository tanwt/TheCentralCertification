package com.jike.certification.model.thirdRole;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.jike.certification.model.jurisdiction.JurisdictionVo;
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
@ApiModel("角色拥有的权限返回类")
public class RoleJurisdictionVo {
    @ApiModelProperty(value = "权限信息")
    private JurisdictionVo jurisdictionVo;
    @ApiModelProperty(value = "是否拥有该权限，false 没有，true 有")
    private boolean haveJurisdiction;
}
