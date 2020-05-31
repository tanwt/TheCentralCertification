package com.jike.certification.biz;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jike.certification.dao.RoleJurisdictionRelevanceDao;
import com.jike.certification.factory.WrapperFactory;
import com.jike.certification.model.roleJurisdictionRelevance.RoleJurisdictionRelevance;
import com.jike.certification.util.CollectionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.List;

/**
 * @author wentong
 * @date 2020-02-11
 */
@Service
public class RoleJurisdictionRelevanceBiz extends ServiceImpl<RoleJurisdictionRelevanceDao, RoleJurisdictionRelevance> {

    @Autowired
    private RoleJurisdictionRelevanceDao relevanceDao;

    public List<RoleJurisdictionRelevance> selectByRoleIdList(List<Long> roleIdList) {
        if (CollectionUtils.isEmpty(roleIdList)) {
            return Collections.EMPTY_LIST;
        }
        QueryWrapper queryWrapper = WrapperFactory.getQueryWrapper();
        queryWrapper.in("role_id", roleIdList);
        return relevanceDao.selectList(queryWrapper);
    }

    public RoleJurisdictionRelevance selectByRoleIdAndJurisdictionId(Long roleId, Long jurisdictionId) {
        QueryWrapper queryWrapper = WrapperFactory.getQueryWrapper();
        queryWrapper.eq("role_id", roleId);
        queryWrapper.eq("jurisdiction_id", jurisdictionId);
        return relevanceDao.selectOne(queryWrapper);
    }

    public List<RoleJurisdictionRelevance> selectByRoleIdListAndJurisdictionId(List<Long> roleIdList, Long jurisdictionId) {
        if (CollectionUtils.isEmpty(roleIdList)) {
            return Collections.EMPTY_LIST;
        }
        QueryWrapper queryWrapper = WrapperFactory.getQueryWrapper();
        queryWrapper.in("role_id", roleIdList);
        queryWrapper.eq("jurisdiction_id", jurisdictionId);
        return relevanceDao.selectList(queryWrapper);
    }
}
