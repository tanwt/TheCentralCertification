package com.jike.certification.biz;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jike.certification.dao.JurisdictionDao;
import com.jike.certification.dao.JurisdictionGroupDao;
import com.jike.certification.factory.WrapperFactory;
import com.jike.certification.model.jurisdiction.Jurisdiction;
import com.jike.certification.model.jurisdictionGroup.JurisdictionGroup;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author wentong
 * @date 2020-02-08
 */

@Service
@Slf4j
public class JurisdictionGroupBiz extends ServiceImpl<JurisdictionGroupDao, JurisdictionGroup> {

    @Autowired
    private JurisdictionGroupDao jurisdictionGroupDao;

    public JurisdictionGroup queryByThirdIdAndName(Long thirdId, String name) {
        QueryWrapper queryWrapper = WrapperFactory.getQueryWrapper();
        queryWrapper.eq("third_id", thirdId);
        queryWrapper.eq("name", name);
        return jurisdictionGroupDao.selectOne(queryWrapper);
    }

    public List<JurisdictionGroup> queryAllByThirdId(Long thirdId) {
        QueryWrapper queryWrapper = WrapperFactory.getQueryWrapper();
        queryWrapper.eq("third_id", thirdId);
        return jurisdictionGroupDao.selectList(queryWrapper);
    }

}
