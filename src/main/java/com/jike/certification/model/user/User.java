package com.jike.certification.model.user;

import lombok.*;

import javax.validation.constraints.NotNull;
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
public class User {
    private Long id;
    private String userName;
    private String password;
    private String mail;
    private Integer deleted;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
}
