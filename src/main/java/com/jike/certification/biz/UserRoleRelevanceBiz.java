package com.jike.certification.biz;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jike.certification.commentEnum.DeleteStatus;
import com.jike.certification.dao.UserRoleRelevanceDao;
import com.jike.certification.factory.WrapperFactory;
import com.jike.certification.model.Pagination;
import com.jike.certification.model.third.Third;
import com.jike.certification.model.userRoleRelevance.UserRoleRelevance;
import com.jike.certification.model.userRoleRelevance.UserRoleRelevancePageReq;
import com.jike.certification.util.CollectionUtil;
import com.jike.certification.util.PageQueryResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.Collections;
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
        if (CollectionUtils.isEmpty(userIdList)) {
            return Collections.EMPTY_LIST;
        }
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

    public List<UserRoleRelevance> queryByThirdId(Long thirdId) {
        QueryWrapper<UserRoleRelevance> queryWrapper = WrapperFactory.getQueryWrapper();
        queryWrapper.eq("third_id", thirdId);
        return userRoleRelevanceDao.selectList(queryWrapper);
    }

    public List<UserRoleRelevance> queryByThirdIdList(List<Long> thirdIdList) {
        if (CollectionUtils.isEmpty(thirdIdList)) {
            return Collections.EMPTY_LIST;
        }
        QueryWrapper<UserRoleRelevance> queryWrapper = WrapperFactory.getQueryWrapper();
        queryWrapper.in("third_id", thirdIdList);
        return userRoleRelevanceDao.selectList(queryWrapper);
    }

    public List<UserRoleRelevance> queryByUserId(Long userId) {
        QueryWrapper<UserRoleRelevance> queryWrapper = WrapperFactory.getQueryWrapper();
        queryWrapper.eq("user_id", userId);
        return userRoleRelevanceDao.selectList(queryWrapper);
    }

    public PageQueryResponse<UserRoleRelevance> userRoleRelevancePage(UserRoleRelevancePageReq userRoleRelevancePageReq){
        QueryWrapper<UserRoleRelevance> queryWrapper = WrapperFactory.getQueryWrapper();
        Pagination pagination = userRoleRelevancePageReq.getPagination();
        Page<UserRoleRelevance> page = new Page<>(pagination.getPageNum(), pagination.getPageSize());
        return PageQueryResponse.buildPageQueryResponse(userRoleRelevanceDao.selectPage(page, queryWrapper));
    }

    public UserRoleRelevance selectByThirdIdAndRoleIdAndUserId(Long thirdId, Long roleId, Long userId) {
        QueryWrapper<UserRoleRelevance> queryWrapper = WrapperFactory.getQueryWrapper();
        queryWrapper.in("third_id", thirdId);
        queryWrapper.in("role_id", roleId);
        queryWrapper.in("user_id", userId);
        return userRoleRelevanceDao.selectOne(queryWrapper);
    }

}
