package com.jike.certification.biz;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jike.certification.dao.UserJurisdictionRelevanceDao;
import com.jike.certification.model.userJurisdictionRelevance.UserJurisdictionRelevance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author wentong
 * @date 2020-02-11
 */
@Service
public class UserJurisdictionRelevanceBiz extends ServiceImpl<UserJurisdictionRelevanceDao, UserJurisdictionRelevance> {

    @Autowired
    private UserJurisdictionRelevanceDao relevanceDao;
}
