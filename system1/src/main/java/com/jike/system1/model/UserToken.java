package com.jike.system1.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;


/**
 * @author wentong
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ApiModel(value = "三方用户信息")
public class UserToken implements Serializable {
    private static final long serialVersionUID = 3107060572331593980L;

    @ApiModelProperty(value = "token 数据库Id")
    private Long id;

    @ApiModelProperty(value = "登录来源的thirdId")
    private Integer thirdId;

    @ApiModelProperty(value = "登陆token", notes = "用户登陆凭证")
    private String token;

    @ApiModelProperty(value = "用户ID")
    private Long userId;

    private LocalDateTime expireTime;
}
