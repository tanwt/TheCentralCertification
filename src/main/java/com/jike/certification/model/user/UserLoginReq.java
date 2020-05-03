package com.jike.certification.model.user;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

/**
 * @author wentong
 * @date 2019-12-26
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ApiModel(value = "用户登陆请求类")
public class UserLoginReq {
    @ApiModelProperty(value = "用户名登陆邮箱，必传")
    @NotNull
    private String mail;
    @ApiModelProperty(value = "用户密码，必传")
    @NotNull
    private String password;
    @ApiModelProperty(value = "平台id，必传")
    private Integer thirdId;
}
