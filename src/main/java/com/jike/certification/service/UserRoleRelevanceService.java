package com.jike.certification.service;

import com.jike.certification.biz.UserRoleRelevanceBiz;
import com.jike.certification.model.userRoleRelevance.UserRoleRelevance;
import com.jike.certification.util.CollectionUtil;
import com.jike.certification.util.MyAssert;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Map;

/**
 * @author wentong
 * @date 2020-01-30
 */
@Service
@Slf4j
public class UserRoleRelevanceService {

    @Autowired
    private UserRoleRelevanceBiz userRoleRelevanceBiz;

    /**
     * 给系统的角色批量新增用户关联
     * 需要避免重复新增
     *
     * @param thirdId
     * @param roleId
     * @param userIdList
     * @return
     */
    public boolean insertBatchToThirdRole(Long thirdId, Long roleId, List<Long> userIdList) {
        MyAssert.notNull(thirdId, "系统Id 不能为空");
        MyAssert.notNull(roleId, "角色Id 不能为空");
        // 查询是否已存在关联
        List<UserRoleRelevance> oldUserRoleRelevanceList = userRoleRelevanceBiz.queryByThirdIdAndRoleIdAndUserIdList(thirdId, roleId, userIdList);
        Map<Long, UserRoleRelevance> oldUserRoleRelevanceMap = CollectionUtil.toMap(oldUserRoleRelevanceList, UserRoleRelevance::getUserId);
        // 去掉已经存在的关联
        userIdList.stream().filter(v -> {
            if (oldUserRoleRelevanceMap.containsKey(v)) {
                return true;
            } else {
                return false;
            }
        });
        return insertBatch(CollectionUtil.transformList(userIdList, v -> {
            return UserRoleRelevance.builder()
                       .thirdId(thirdId)
                       .roleId(roleId)
                       .userId(v)
                       .build();
        }));
    }

    /**
     * 批量新增用户角色关联
     *
     * @param userRoleRelevanceList
     * @return
     */
    public boolean insertBatch(List<UserRoleRelevance> userRoleRelevanceList) {
        if (CollectionUtils.isEmpty(userRoleRelevanceList)) {
            log.warn("批量新增用户角色关联：无可新增的关联");
            return true;
        }
        return userRoleRelevanceBiz.saveBatch(userRoleRelevanceList);
    }

    /**
     * 删除某个角色对应的用户角色关联
     * @param roleId
     * @return
     */
    public boolean deletedByRoleId(Long roleId) {
        MyAssert.notNull(roleId, "角色Id 为空");
        List<UserRoleRelevance> userRoleRelevanceList = userRoleRelevanceBiz.queryByRoleId(roleId);
        List<Long> userIdList = CollectionUtil.transformList(userRoleRelevanceList, UserRoleRelevance::getUserId);
        return deletedBatch(userIdList);
    }

    /**
     * 批量用户角色删除关联
     * @param idList
     * @return
     */
    public boolean deletedBatch(List<Long> idList) {
        if (CollectionUtils.isEmpty(idList)) {
            log.warn("批量用户角色删除关联：无可删除的关联");
            return true;
        }
        return userRoleRelevanceBiz.deleteBatch(idList);
    }
}
