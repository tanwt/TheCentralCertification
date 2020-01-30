package com.jike.certification.model.userRoleRelevance;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author wentong
 * @date 2020-01-29
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@TableName("user_role_relevance")
public class UserRoleRelevance extends Model<UserRoleRelevance> {
    @TableId(type = IdType.AUTO)
    private Long id;
    private Long userId;
    private Long roleId;
    private Long thirdId;

    private Integer deleted;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
}
