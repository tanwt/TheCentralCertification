package com.jike.certification.model.user;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import lombok.*;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author wentong
 * @date 2019-12-26
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@TableName("user")
public class User extends Model<User> {
    private Long id;
    @NotNull
    private String userName;
    @NotNull
    private String password;
    @NotNull
    private String mail;
    private Integer deleted;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;

    @Override
    protected Serializable pkVal() {
        return id;
    }
}
