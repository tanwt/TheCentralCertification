package com.jike.certification.model.userJurisdictionRelevance;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.*;

import java.time.LocalDateTime;

/**
 * @author wentong
 * @date 2020-02-11
 */
@Data
@Builder
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@TableName("user_jurisdiction_relevance")
public class UserJurisdictionRelevance extends Model<UserJurisdictionRelevance> {
    @TableId(type = IdType.AUTO)
    private Long id;
    private Long userId;
    private Long jurisdictionId;
    private Integer status;
    private Integer deleted;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
}
