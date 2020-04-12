package com.jike.certification.service;

import com.jike.certification.biz.JurisdictionBiz;
import com.jike.certification.biz.JurisdictionGroupBiz;
import com.jike.certification.model.jurisdiction.*;
import com.jike.certification.model.jurisdictionGroup.JurisdictionGroup;
import com.jike.certification.model.jurisdictionGroup.JurisdictionGroupListVo;
import com.jike.certification.model.jurisdictionGroup.JurisdictionGroupVo;
import com.jike.certification.util.CollectionUtil;
import com.jike.certification.util.MyAssert;
import com.jike.certification.util.MyBeanUtils;
import com.jike.certification.util.StringUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * @author wentong
 * @date 2020-02-08
 */
@Service
@Slf4j
public class JurisdictionService {

    @Autowired
    private JurisdictionBiz jurisdictionBiz;

    @Autowired
    private JurisdictionGroupBiz jurisdictionGroupBiz;

    /**
     * 给某个系统新增权限
     * 根据名称去重
     *
     * @return
     */
    public JurisdictionVo addJurisdiction(JurisdictionInsertReq insertReq) {
        MyAssert.notNull(insertReq, "权限新增数据为空");
        MyAssert.notNull(insertReq.getThirdId(), "权限新增数据: 系统ID 为空");
        MyAssert.notNull(insertReq.getJurisdictionGroupId(), "权限新增数据: 权限组ID 为空");
        MyAssert.notNull(insertReq.getName(), "权限新增数据: 权限名称为空");
        Jurisdiction jurisdiction = MyBeanUtils.myCopyProperties(insertReq, new Jurisdiction());
        Jurisdiction oldJurisdiction = jurisdictionBiz.queryByGroupIdAndName(jurisdiction.getJurisdictionGroupId(), jurisdiction.getName());
        MyAssert.isNull(oldJurisdiction, "该权限已存在");
        jurisdictionBiz.save(jurisdiction);
        Jurisdiction newJurisdiction = jurisdictionBiz.getById(jurisdiction.getId());
        return MyBeanUtils.myCopyProperties(newJurisdiction, new JurisdictionVo());
    }

    /**
     * 更新权限
     * 新权限名根据名字去重
     *
     * @param updateReq
     * @return
     */
    public JurisdictionVo updateJurisdiction(JurisdictionUpdateReq updateReq) {
        MyAssert.notNull(updateReq, "权限更新数据为空");
        MyAssert.notNull(updateReq.getId(), "权限更新数据: 权限ID 为空");
        if (StringUtil.checkNotEmpty(updateReq.getName())) {
            Jurisdiction oldJurisdiction = jurisdictionBiz.queryByGroupIdAndName(updateReq.getJurisdictionGroupId(), updateReq.getName());
            MyAssert.isNull(oldJurisdiction, "该权限已存在");
        }
        jurisdictionBiz.updateById(MyBeanUtils.myCopyProperties(updateReq, new Jurisdiction()));
        Jurisdiction newJurisdiction = jurisdictionBiz.getById(updateReq.getId());
        return MyBeanUtils.myCopyProperties(newJurisdiction, new JurisdictionVo());
    }

    /**
     * 查询权限列表
     *
     * @param listReq
     * @return
     */
    public List<JurisdictionListVo> queryJurisdictionList(JurisdictionListReq listReq) {
        MyAssert.notNull(listReq, "查询权限列表数据为空");
        List<Jurisdiction> jurisdictions = jurisdictionBiz.queryJurisdictionList(listReq);
        if (CollectionUtils.isEmpty(jurisdictions)) {
            return Collections.EMPTY_LIST;
        }
        List<Long> groupIdList = CollectionUtil.transformList(jurisdictions, Jurisdiction::getJurisdictionGroupId);
        List<JurisdictionGroup> jurisdictionGroupList = jurisdictionGroupBiz.listByIds(groupIdList);
        Map<Long, JurisdictionGroup> groupMap = CollectionUtil.toMap(jurisdictionGroupList, JurisdictionGroup::getId);
        return CollectionUtil.transformList(jurisdictions, v -> {
            JurisdictionListVo jurisdictionListVo = MyBeanUtils.myCopyProperties(v, new JurisdictionListVo());
            JurisdictionGroupVo groupListVo = MyBeanUtils.myCopyProperties(groupMap.get(v.getJurisdictionGroupId()), new JurisdictionGroupVo());
            jurisdictionListVo.setJurisdictionGroupVo(groupListVo);
            return jurisdictionListVo;
        });
    }


}
