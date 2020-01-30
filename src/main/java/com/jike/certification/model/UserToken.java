package com.jike.certification.model;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
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
@TableName("user_token")
public class UserToken extends Model<UserToken> {

    @ApiModelProperty(value = "token 数据库Id")
    @TableId(type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "登录来源的thirdId")
    private Integer thirdId;

    @ApiModelProperty(value = "登陆token", notes = "用户登陆凭证")
    private String token;

    @ApiModelProperty(value = "用户ID")
    private Long userId;

    private LocalDateTime expireTime;
}
