package com.jike.certification.model.roleJurisdictionRelevance;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

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
@ApiModel("用户权限关联返回")
public class RoleJurisdictionRelevanceVo extends Model<RoleJurisdictionRelevanceVo> {
    @ApiModelProperty("用户权限关联id")
    private Long id;
    @ApiModelProperty("用户权限关联角色id")
    private Long roleId;
    @ApiModelProperty("用户权限权限id")
    private Long jurisdictionId;
}
