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
    @NotNull(message = "用户名不能为空")
    private String userName;
    @ApiModelProperty(value = "邮箱 ")
    @NotNull(message = "邮箱不能为空")
    private String mail;
    @ApiModelProperty(value = "用户密码 ")
    @NotNull(message = "密码不能为空")
    private String password;
    @ApiModelProperty(value = "验证码")
    @NotNull(message = "验证码不能为空")
    private String verifyCode;
}
