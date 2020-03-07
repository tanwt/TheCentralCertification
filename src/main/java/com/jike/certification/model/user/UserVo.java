package com.jike.certification.model.user;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

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
@ApiModel("用户标准返回")
public class UserVo {
    @ApiModelProperty("用户自增id")
    private Long id;
    @ApiModelProperty("用户名")
    private String userName;
    @ApiModelProperty("用户邮箱")
    private String mail;
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty("用户创建时间")
    private LocalDateTime createTime;
}
