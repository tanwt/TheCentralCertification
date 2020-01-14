package com.jike.certification.model.third;

import io.swagger.annotations.ApiModel;
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
@ApiModel(value = "第三饭平台基础类")
public class Third {
    private Long id;
    private String name;
    private Integer deleted;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
}
