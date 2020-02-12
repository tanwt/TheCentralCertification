package com.jike.certification.model.roleJurisdictionRelevance;

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
@TableName("role_jurisdiction_relevance")
public class RoleJurisdictionRelevance extends Model<RoleJurisdictionRelevance> {
    @TableId(type = IdType.AUTO)
    private Long id;
    private Long roleId;
    private Long jurisdictionId;
    private Integer status;
    private Integer deleted;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
}
