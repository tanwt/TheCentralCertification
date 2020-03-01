package com.jike.certification.model.jurisdiction;

import com.alibaba.fastjson.annotation.JSONField;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

/**
 * @author wentong
 * @date 2020-02-08
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@TableName("jurisdiction")
public class Jurisdiction extends Model<Jurisdiction> {
    @TableId(type = IdType.AUTO)
    private Long id;
    private Long thirdId;
    private Long jurisdictionGroupId;
    private String name;
    private String jurisdictionExplain;

    private Integer deleted;
    private LocalDateTime createTime;
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updateTime;
}
