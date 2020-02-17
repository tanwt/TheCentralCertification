package com.jike.certification.service;

import com.jike.certification.biz.JurisdictionBiz;
import com.jike.certification.biz.JurisdictionGroupBiz;
import com.jike.certification.commentEnum.DeleteStatus;
import com.jike.certification.model.jurisdiction.Jurisdiction;
import com.jike.certification.model.jurisdictionGroup.JurisdictionGroup;
import com.jike.certification.model.jurisdictionGroup.JurisdictionGroupInsertReq;
import com.jike.certification.model.jurisdictionGroup.JurisdictionGroupListVo;
import com.jike.certification.model.jurisdictionGroup.JurisdictionGroupUpdateReq;
import com.jike.certification.util.CollectionUtil;
import com.jike.certification.util.MyAssert;
import com.jike.certification.util.MyBeanUtils;
import com.jike.certification.util.StringUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;

/**
 * @author wentong
 * @date 2020-02-08
 */
@Service
@Slf4j
public class JurisdictionGroupService {

    @Autowired
    private JurisdictionGroupBiz groupBiz;
    @Autowired
    private JurisdictionBiz jurisdictionBiz;

    /**
     * 给某个系统新增权限组
     * 根据名称去重
     *
     * @return
     */
    public boolean addJurisdictionGroup(JurisdictionGroupInsertReq insertReq) {
        MyAssert.notNull(insertReq, "权限组新增数据为空");
        MyAssert.notNull(insertReq.getThirdId(), "权限组新增数据: 系统ID 为空");
        MyAssert.notNull(insertReq.getName(), "权限组新增数据: 权限组名称为空");
        JurisdictionGroup jurisdictionGroup = MyBeanUtils.myCopyProperties(insertReq, new JurisdictionGroup());
        JurisdictionGroup oldJurisdictionGroup = groupBiz.queryByThirdIdAndName(jurisdictionGroup.getThirdId(), jurisdictionGroup.getName());
        MyAssert.isNull(oldJurisdictionGroup, "该权限组已存在");
        return groupBiz.save(jurisdictionGroup);
    }

    /**
     * 更新权限组信息
     * 如果需要更新权限组名字，根据系统和权限组新名去重
     *
     * @param updateReq
     * @return
     */
    public boolean updateJurisdictionGroup(JurisdictionGroupUpdateReq updateReq) {
        MyAssert.notNull(updateReq, "权限组更新数据为空");
        MyAssert.notNull(updateReq.getId(), "权限组更新数据: 权限组id 为空");
        if(StringUtil.checkNotEmpty(updateReq.getName())) {
            JurisdictionGroup oldJurisdictionGroup = groupBiz.queryByThirdIdAndName(updateReq.getThirdId(), updateReq.getName());
            MyAssert.isNull(oldJurisdictionGroup, "系统已经存在该权限组");
        }
        return groupBiz.updateById(MyBeanUtils.myCopyProperties(updateReq, new JurisdictionGroup()));
    }

    /**
     * 删除权限组
     * 附带删除权限组下的权限
     * @param groupId
     * @return
     */
    @Transactional(rollbackFor=Exception.class)
    public boolean deletedGroup(Long groupId) {
        MyAssert.notNull(groupId, "删除权限组：id 为空");
        boolean flag ;
        JurisdictionGroup group = JurisdictionGroup.builder()
                                      .id(groupId)
                                      .deleted(DeleteStatus.DELETED.getValue())
                                      .build();
        flag = groupBiz.updateById(group);
        List<Jurisdiction> jurisdictions = jurisdictionBiz.queryByGroupId(groupId);
        // 删除权限组下的权限
        jurisdictions.forEach(jurisdiction -> jurisdiction.setDeleted(DeleteStatus.DELETED.getValue()));
        flag = jurisdictionBiz.updateBatchById(jurisdictions, jurisdictions.size());
        return flag;
    }

    /**
     * 获取一个系统下的权限组
     *
     * @param thirdId
     * @return
     */
    public List<JurisdictionGroupListVo> getAllGroupByThirdId(Long thirdId) {
        if (thirdId == null) {
            log.warn("查找一个系统下的所有权限组，出现未知系统id: {}", thirdId);
            return Collections.EMPTY_LIST;
        }
        List<JurisdictionGroup> jurisdictionGroupList = groupBiz.queryAllByThirdId(thirdId);
        return CollectionUtil.transformList(jurisdictionGroupList, v -> {
            return MyBeanUtils.myCopyProperties(v, new JurisdictionGroupListVo());
        });
    }
}
