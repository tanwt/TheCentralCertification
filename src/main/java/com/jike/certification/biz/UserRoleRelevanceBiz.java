package com.jike.certification.biz;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jike.certification.commentEnum.DeleteStatus;
import com.jike.certification.dao.UserRoleRelevanceDao;
import com.jike.certification.factory.WrapperFactory;
import com.jike.certification.model.userRoleRelevance.UserRoleRelevance;
import com.jike.certification.util.CollectionUtil;
import com.jike.certification.util.PageQueryResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * @author wentong
 * @date 2020-01-29
 */
@Service
public class UserRoleRelevanceBiz extends ServiceImpl<UserRoleRelevanceDao, UserRoleRelevance> {

    @Autowired
    private UserRoleRelevanceDao userRoleRelevanceDao;

    public List<UserRoleRelevance> queryByThirdIdAndRoleIdAndUserIdList(Long thirdId, Long roleId, List<Long> userIdList){
        QueryWrapper<UserRoleRelevance> queryWrapper = WrapperFactory.getQueryWrapper();
        queryWrapper.eq("third_id", thirdId);
        queryWrapper.eq("role_id", roleId);
        queryWrapper.in("user_id", userIdList);
        return userRoleRelevanceDao.selectList(queryWrapper);
    }

    public boolean deleteBatch(List<Long> idList) {
        if (CollectionUtils.isEmpty(idList)) {
            return false;
        }
        List<UserRoleRelevance> userRoleRelevanceList = CollectionUtil.transformList(idList, v -> {
            return UserRoleRelevance.builder()
                       .id(v)
                       .deleted(DeleteStatus.DELETED.getValue())
                       .build();
        });
        return updateBatchById(userRoleRelevanceList);
    }

    public List<UserRoleRelevance> queryByRoleId(Long roleId) {
        QueryWrapper<UserRoleRelevance> queryWrapper = WrapperFactory.getQueryWrapper();
        queryWrapper.eq("role_id", roleId);
        return userRoleRelevanceDao.selectList(queryWrapper);
    }
}
