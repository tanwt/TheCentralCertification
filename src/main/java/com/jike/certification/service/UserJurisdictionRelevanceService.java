package com.jike.certification.service;

import com.jike.certification.biz.UserJurisdictionRelevanceBiz;
import com.jike.certification.model.userJurisdictionRelevance.UserJurisdictionRelevance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author wentong
 * @date 2020-02-11
 */
@Service
public class UserJurisdictionRelevanceService {

    @Autowired
    private UserJurisdictionRelevanceBiz relevanceBiz;
}
