package com.jike.certification.util;

import com.baomidou.mybatisplus.core.metadata.IPage;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.function.Function;

/**
 * Created by liuhongjie on 2019/2/20.
 * 封装需要分页的数据
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Slf4j
public class PageQueryResponse<T> {
    private List<T> dataList;
    private Long page;
    private Long total;

    public static <U> PageQueryResponse<U> buildPageQueryResponse(IPage<U> page) {
        return PageQueryResponse.<U>builder()
                   .dataList(page.getRecords())
                   .page(page.getCurrent())
                   .total(page.getTotal())
                   .build();
    }


    /**
     * 处理dataList转换成一个新的PageQueryResponse
     *
     * @param function
     * @param <U>
     * @return
     */
    public <U> PageQueryResponse<U> transform(Function<T, U> function) {
        List<U> resultList = CollectionUtil.transformList(dataList, function);
        return PageQueryResponse.<U>builder()
                   .dataList(resultList)
                   .page(page)
                   .total(total)
                   .build();
    }
}
