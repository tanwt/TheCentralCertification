package com.jike.certification.service;

import com.jike.certification.biz.RoleJurisdictionRelevanceBiz;
import com.jike.certification.commentEnum.DeleteStatus;
import com.jike.certification.model.roleJurisdictionRelevance.RoleJurisdictionRelevance;
import com.jike.certification.model.roleJurisdictionRelevance.RoleJurisdictionRelevanceInsertReq;
import com.jike.certification.model.roleJurisdictionRelevance.RoleJurisdictionRelevanceVo;
import com.jike.certification.model.thirdRole.RoleJurisdictionVo;
import com.jike.certification.util.MyAssert;
import com.jike.certification.util.MyBeanUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author wentong
 * @date 2020-02-11
 */
@Service
@Slf4j
public class RoleJurisdictionRelevanceService {

    @Autowired
    private RoleJurisdictionRelevanceBiz relevanceBiz;

    /**
     * 查询或新增角色权限关联
     *
     * @param insertReq
     * @return
     */
    public RoleJurisdictionRelevanceVo getOrAddRel(RoleJurisdictionRelevanceInsertReq insertReq) {
        MyAssert.notNull(insertReq, "新增用户权限关联数据为空");
        MyAssert.notNull(insertReq.getRoleId(), "新增用户权限关联角色id 数据为空");
        MyAssert.notNull(insertReq.getJurisdictionId(), "新增用户权限关联权限id 数据为空");
        RoleJurisdictionRelevance oldRel = relevanceBiz.selectByRoleIdAndJurisdictionId(insertReq.getRoleId(), insertReq.getJurisdictionId());
        if (oldRel == null) {
            RoleJurisdictionRelevance insert = MyBeanUtils.myCopyProperties(insertReq, new RoleJurisdictionRelevance());
            relevanceBiz.save(insert);
            RoleJurisdictionRelevance newRel = relevanceBiz.getById(insert.getId());
            return MyBeanUtils.myCopyProperties(newRel, new RoleJurisdictionRelevanceVo());
        } else {
            log.info("已经存在用户权限关联:{}", oldRel);
            return MyBeanUtils.myCopyProperties(oldRel, new RoleJurisdictionRelevanceVo());
        }
    }

    public boolean deletedRel(Long relId) {
        MyAssert.notNull(relId, "要删除的用户权限关联id 为空");
        RoleJurisdictionRelevance roleJurisdictionRelevance = RoleJurisdictionRelevance.builder()
                                                                  .id(relId)
                                                                  .deleted(DeleteStatus.DELETED.getValue())
                                                                  .build();
        return relevanceBiz.updateById(roleJurisdictionRelevance);
    }

}
