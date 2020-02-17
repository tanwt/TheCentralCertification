package com.jike.certification.model.jurisdictionGroup;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.*;

import java.time.LocalDateTime;

/**
 * @author wentong
 * @date 2020-02-08
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@TableName("jurisdiction_group")
public class JurisdictionGroup extends Model<JurisdictionGroup> {
    @TableId(type = IdType.AUTO)
    private Long id;
    private Long thirdId;
    private String name;
    private String typeExplain;

    private Integer deleted;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
}
