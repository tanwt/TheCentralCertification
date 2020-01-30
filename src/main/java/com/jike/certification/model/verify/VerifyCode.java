package com.jike.certification.model.verify;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.*;

import java.time.LocalDateTime;

/**
 * @author wentong
 * @date 2019-12-27
 */
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@TableName("verify_code")
public class VerifyCode extends Model<VerifyCode> {
    @TableId(type = IdType.AUTO)
    private Long id;
    private String mobile;
    private String mail;
    private String code;
    private String verifyCode;
    private String applyIp;
    private String validateIp;
    private Integer sendTimes;
    private Integer status;
    private Integer deleted;
    private LocalDateTime validateTime;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
}
