package com.jike.certification.util;

import com.github.pagehelper.Page;
import com.jike.certification.model.Pagination;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

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
    private Integer page;
    private Integer total;

    public List<T> getDataList() {
        return dataList;
    }

    public void setDataList(List<T> dataList) {
        this.dataList = dataList;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public static <U> PageQueryResponse<U> fromPageHelperResult(List<U> list) {
        Page page = (Page) list;
        log.info("fromPageHelperResult, list.size={}", list.size());
        return PageQueryResponse.<U>builder()
                   .dataList(list)
                   .page(page.getPageNum())
                   // 如果查询总条数超过int的范围，性能肯定非常差，是不允许这种情况存在的，所以这里可以安全地做类型转换
                   .total((int) page.getTotal())
                   .build();
    }

    public static <U> PageQueryResponse<U> buildFromPageHelperSupplier(
        Pagination pagination, int maxPageSize, Function<Pagination, List<U>> function) {
        pagination = Pagination.safePagination(pagination, maxPageSize);

        log.info("pagination={}", pagination);
        List<U> list = function.apply(pagination);
        return fromPageHelperResult(list);
    }


    /**
     * 处理dataList转换成一个新的PageQueryResponse
     *
     * @param function
     * @param <U>
     * @return
     */
    public <U> PageQueryResponse<U> transform(Function<T, U> function) {
        List<U> resultList = dataList.stream().map(function).collect(Collectors.toList());
        return PageQueryResponse.<U>builder()
                   .dataList(resultList)
                   .page(page)
                   .total(total)
                   .build();
    }
}
