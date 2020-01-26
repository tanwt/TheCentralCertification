package com.jike.certification.model.third;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import io.swagger.annotations.ApiModel;
import lombok.*;

import java.io.Serializable;
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
@ApiModel(value = "第三饭平台基础类")
public class Third extends Model<Third> {
    private Long id;
    private String name;
    private Integer deleted;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;

    @Override
    protected Serializable pkVal() {
        return id;
    }
}
