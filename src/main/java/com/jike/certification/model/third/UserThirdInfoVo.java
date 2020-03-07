package com.jike.certification.model.third;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.jike.certification.model.thirdRole.ThirdRole;
import com.jike.certification.model.thirdRole.ThirdRoleVo;
import com.jike.certification.model.userRoleRelevance.UserRoleRelevanceInfoVo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author wentong
 * @date 2020-01-13
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ApiModel(value = "用户的第三方平台信息返回类")
public class UserThirdInfoVo {
    @ApiModelProperty(value = "系统信息")
    private ThirdVo thirdVo;
    @ApiModelProperty(value = "用户在系统下的用户组信息")
    private List<UserRoleRelevanceInfoVo> userRoleRelevanceInfoVoList;
}
