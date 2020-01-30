package com.jike.certification.model.thirdRole;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * @author wentong
 * @date 2020-01-27
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@TableName("third_role")
public class ThirdRole extends Model<ThirdRole> {
    @TableId(type = IdType.AUTO)
    private Long id;
    private Long thirdId;
    private String name;

    private Integer deleted;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;

}
