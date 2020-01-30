package com.jike.certification.model.third;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.*;

import java.time.LocalDateTime;

/**
 * @author wentong
 * @date 2020-01-13
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@TableName("third")
public class Third extends Model<Third> {
    @TableId(type = IdType.AUTO)
    private Long id;
    private String name;
    private Integer deleted;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;

}
