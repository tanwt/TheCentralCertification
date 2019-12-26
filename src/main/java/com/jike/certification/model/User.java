package com.jike.certification.model;

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
    @NotNull
    private String userName;
    @NotNull
    private String password;
    private Integer deleted;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
}
