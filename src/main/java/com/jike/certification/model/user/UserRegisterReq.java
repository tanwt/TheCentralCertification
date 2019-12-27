package com.jike.certification.model.user;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.validation.constraints.NotNull;

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
public class UserRegisterReq {
    @ApiModelProperty(value = "用户名 ")
    @NotNull
    private String userName;
    @ApiModelProperty(value = "邮箱 ")
    @NotNull
    private String mail;
    @ApiModelProperty(value = "用户密码 ")
    @NotNull
    private String password;
    @ApiModelProperty(value = "验证码")
    @NotNull
    private String verifyCode;
}
