package com.jike.certification.service;

import com.jike.certification.biz.UserRoleRelevanceBiz;
import com.jike.certification.commentEnum.DeleteStatus;
import com.jike.certification.model.user.UserVo;
import com.jike.certification.model.userRoleRelevance.*;
import com.jike.certification.util.CollectionUtil;
import com.jike.certification.util.MyAssert;
import com.jike.certification.util.MyBeanUtils;
import com.jike.certification.util.PageQueryResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.Collections;
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

    @Autowired
    private UserService userService;

    /**
     * 新增一个用户角色关联
     * @param userRoleRelevanceInsert
     * @return
     */
    public Long addUserRoleRelevance(UserRoleRelevanceInsert userRoleRelevanceInsert) {
        MyAssert.notNull(userRoleRelevanceInsert, "用户角色关联新增,数据为空");
        MyAssert.notNull(userRoleRelevanceInsert.getThirdId(), "用户角色关联新增, 系统id 数据为空");
        MyAssert.notNull(userRoleRelevanceInsert.getRoleId(), "用户角色关联新增,角色id 数据为空");
        MyAssert.notNull(userRoleRelevanceInsert.getUserId(), "用户角色关联新增, 用户id 数据为空");
        UserRoleRelevance oldRel = userRoleRelevanceBiz.selectByThirdIdAndRoleIdAndUserId(userRoleRelevanceInsert.getThirdId(),
            userRoleRelevanceInsert.getRoleId(),
            userRoleRelevanceInsert.getUserId());
        if (oldRel == null) {
            UserRoleRelevance newRel = MyBeanUtils.myCopyProperties(userRoleRelevanceInsert, new UserRoleRelevance());
            userRoleRelevanceBiz.save(newRel);
            return newRel.getId();
        } else {
            return oldRel.getId();
        }

    }

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
        // 存在的用户才可以插入关联
        List<UserVo> userVoList = userService.queryByUserIdList(userIdList);
        userIdList = CollectionUtil.transformList(userVoList, UserVo::getId);
        // 查询是否已存在关联
        List<UserRoleRelevance> oldUserRoleRelevanceList = userRoleRelevanceBiz.queryByThirdIdAndRoleIdAndUserIdList(thirdId, roleId, userIdList);
        Map<Long, UserRoleRelevance> oldUserRoleRelevanceMap = CollectionUtil.toMap(oldUserRoleRelevanceList, UserRoleRelevance::getUserId);
        // 去掉已经存在的关联
        userIdList = CollectionUtil.filter(userIdList, v -> {
            if (oldUserRoleRelevanceMap.containsKey(v)) {
                return false;
            } else {
                return true;
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
     * 用户角色关联分页接口
     *
     * @param pageReq
     * @return
     */
    public PageQueryResponse<UserRoleRelevancePageVo> userRoleRelevancePage(UserRoleRelevancePageReq pageReq) {
        MyAssert.notNull(pageReq, "用户角色关联分页请求数据为空");
        MyAssert.notNull(pageReq.getPagination(), "用户角色关联分页参数为空");
        // 关联用户数据
        PageQueryResponse<UserRoleRelevance> pageQueryResponse = userRoleRelevanceBiz.userRoleRelevancePage(pageReq);
        List<UserRoleRelevance> dataList = pageQueryResponse.getDataList();
        List<Long> userIdList = CollectionUtil.transformListDistinct(dataList, UserRoleRelevance::getUserId);
        List<UserVo> userVoList = userService.queryByUserIdList(userIdList);
        Map<Long, UserVo> userVoMap = CollectionUtil.toMap(userVoList, UserVo::getId);
        return pageQueryResponse.transform(v -> {
            UserRoleRelevancePageVo userRoleRelevancePageVo = MyBeanUtils.myCopyProperties(v, new UserRoleRelevancePageVo());
            userRoleRelevancePageVo.setUserVo(userVoMap.get(v.getUserId()));
            return userRoleRelevancePageVo;
        });
    }

    /**
     * 返回一个系统的用户角色关联
     *
     * @param thirdId
     * @return
     */
    public List<UserRoleRelevanceListVo> userRoleRelevanceList(Long thirdId) {
        if (thirdId == null) {
            return Collections.EMPTY_LIST;
        }
        List<UserRoleRelevance> userRoleRelevanceList = userRoleRelevanceBiz.queryByThirdId(thirdId);
        // 关联用户
        List<Long> userIdList = CollectionUtil.transformListDistinct(userRoleRelevanceList, UserRoleRelevance::getUserId);
        List<UserVo> userVoList = userService.queryByUserIdList(userIdList);
        Map<Long, UserVo> userVoMap = CollectionUtil.toMap(userVoList, UserVo::getId);
        List<UserRoleRelevanceListVo> userRoleRelevanceListVoList = CollectionUtil.transformList(userRoleRelevanceList, v -> {
            UserRoleRelevanceListVo userRoleRelevanceListVo = MyBeanUtils.myCopyProperties(v, new UserRoleRelevanceListVo());
            userRoleRelevanceListVo.setUserVo(userVoMap.get(v.getUserId()));
            return userRoleRelevanceListVo;
        });
        return userRoleRelevanceListVoList;
    }

    /**
     * 删除某个角色对应的用户角色关联
     *
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
     * 删除某个用户角色关联
     *
     * @return
     */
    public boolean deletedRelevance(Long relevanceId) {
        MyAssert.notNull(relevanceId, "要删除的关联id 为空");
        UserRoleRelevance userRoleRelevance = UserRoleRelevance.builder()
                                                  .id(relevanceId)
                                                  .deleted(DeleteStatus.DELETED.getValue())
                                                  .build();
        return userRoleRelevanceBiz.updateById(userRoleRelevance);
    }

    /**
     * 批量用户角色删除关联
     *
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
