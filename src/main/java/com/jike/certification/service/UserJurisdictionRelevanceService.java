package com.jike.certification.service;

import com.jike.certification.biz.JurisdictionBiz;
import com.jike.certification.biz.RoleJurisdictionRelevanceBiz;
import com.jike.certification.biz.UserJurisdictionRelevanceBiz;
import com.jike.certification.biz.UserRoleRelevanceBiz;
import com.jike.certification.model.jurisdiction.Jurisdiction;
import com.jike.certification.model.roleJurisdictionRelevance.RoleJurisdictionRelevance;
import com.jike.certification.model.userJurisdictionRelevance.UserJurisdictionRelevance;
import com.jike.certification.model.userRoleRelevance.UserRoleRelevance;
import com.jike.certification.util.CollectionUtil;
import com.jike.certification.util.ContextUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * @author wentong
 * @date 2020-02-11
 */
@Service
@Slf4j
public class UserJurisdictionRelevanceService {

    @Autowired
    private JurisdictionBiz jurisdictionBiz;
    @Autowired
    private RoleJurisdictionRelevanceBiz roleJurisdictionRelevanceBiz;
    @Autowired
    private UserRoleRelevanceBiz userRoleRelevanceBiz;

    /**
     * 检查用户是否具有路径的访问权限
     * @param userId
     * @param jurisdictionExplain
     * @return
     */
    public boolean checkUserJurisdiction(Long userId, String jurisdictionExplain) {
        Jurisdiction jurisdiction = jurisdictionBiz.queryByExplain(jurisdictionExplain);
        if (jurisdiction == null) {
            return true;
        }
        List<UserRoleRelevance> userRoleRelevanceList = userRoleRelevanceBiz.queryByUserId(ContextUtil.getUserId());
        List<Long> roleIdList = CollectionUtil.transformList(userRoleRelevanceList, UserRoleRelevance::getRoleId);
        // 如果
        List<RoleJurisdictionRelevance> roleJurisdictionRelevanceList = roleJurisdictionRelevanceBiz.selectByRoleIdListAndJurisdictionId(roleIdList, jurisdiction.getId());
        if (CollectionUtils.isEmpty(roleJurisdictionRelevanceList)) {
            log.info("用户没有路径访问权限,userId: {}, jurisdictionExplain: {}", userId, jurisdictionExplain);
            return false;
        }
        return true;
    }
}
