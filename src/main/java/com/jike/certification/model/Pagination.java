package com.jike.certification.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author wxl
 * @date 2019-03-10
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "分页基础类")
public class Pagination implements Serializable {
    private static final long serialVersionUID = -7441943820360560018L;
    public static int MAX_PAGE_SIZE = 100;

    @ApiModelProperty(value = "分页大小")
    private Integer pageSize;
    @ApiModelProperty(value = "分页页码")
    private Integer pageNum;

    /**
     * 确保pagination对象是安全的：pageNum和pageSize有合理的值
     *
     * @param pageSize
     */
    public static Pagination safePagination(Pagination pagination, int pageSize) {
        if (pagination == null) {
            return Pagination.builder().pageNum(1).pageSize(pageSize).build();
        }

        if (pagination.pageSize == null || pagination.pageSize > pageSize) {
            pagination.pageSize = pageSize;
        }

        if (pagination.pageNum == null || pagination.pageNum <= 0) {
            pagination.pageNum = 1;
        }
        return pagination;
    }
}
