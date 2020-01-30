package com.jike.certification.factory;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.jike.certification.commentEnum.DeleteStatus;
import com.jike.certification.model.userRoleRelevance.UserRoleRelevance;

/**
 * @author wentong
 * @date 2020-01-30
 */
public class WrapperFactory {
    public static <T> QueryWrapper getQueryWrapper() {
        QueryWrapper<T> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("deleted", DeleteStatus.NOT_DELETED.getValue());
        return queryWrapper;
    }
}
