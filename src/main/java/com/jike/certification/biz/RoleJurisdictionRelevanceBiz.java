package com.jike.certification.biz;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jike.certification.dao.RoleJurisdictionRelevanceDao;
import com.jike.certification.model.roleJurisdictionRelevance.RoleJurisdictionRelevance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author wentong
 * @date 2020-02-11
 */
@Service
public class RoleJurisdictionRelevanceBiz extends ServiceImpl<RoleJurisdictionRelevanceDao, RoleJurisdictionRelevance> {

    @Autowired
    private RoleJurisdictionRelevanceDao relevanceDao;
}
