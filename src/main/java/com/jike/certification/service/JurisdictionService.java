package com.jike.certification.service;

import com.jike.certification.biz.JurisdictionBiz;
import com.jike.certification.model.jurisdiction.Jurisdiction;
import com.jike.certification.model.jurisdiction.JurisdictionInsertReq;
import com.jike.certification.util.CollectionUtil;
import com.jike.certification.util.MyAssert;
import com.jike.certification.util.MyBeanUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    /**
     * 给某个系统新增权限
     * 根据名称去重
     * 
     * @return
     */
    public boolean addJurisdiction(JurisdictionInsertReq insertReq) {
        MyAssert.notNull(insertReq, "权限新增数据为空");
        MyAssert.notNull(insertReq.getThirdId(), "权限新增数据: 系统ID 为空");
        MyAssert.notNull(insertReq.getName(), "权限新增数据: 权限名称为空");
        Jurisdiction jurisdiction = MyBeanUtils.myCopyProperties(insertReq, new Jurisdiction());
        Jurisdiction oldJurisdiction = jurisdictionBiz.queryByThirdIdAndName(jurisdiction.getThirdId(), jurisdiction.getName());
        MyAssert.isNull(oldJurisdiction, "该权限已存在");
        return jurisdictionBiz.save(jurisdiction);
    }
}
