package com.jike.certification.biz;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jike.certification.dao.JurisdictionDao;
import com.jike.certification.factory.WrapperFactory;
import com.jike.certification.model.jurisdiction.Jurisdiction;
import com.jike.certification.util.CollectionUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.List;

/**
 * @author wentong
 * @date 2020-02-08
 */

@Service
@Slf4j
public class JurisdictionBiz extends ServiceImpl<JurisdictionDao, Jurisdiction> {

    @Autowired
    private JurisdictionDao jurisdictionDao;

    public Jurisdiction queryByThirdIdAndName(Long thirdId, String name){
        QueryWrapper queryWrapper = WrapperFactory.getQueryWrapper();
        queryWrapper.eq("third_id", thirdId);
        queryWrapper.eq("name", name);
        return jurisdictionDao.selectOne(queryWrapper);
    }

    public List<Jurisdiction> queryByThirdIdAndNameList(Long thirdId, List<String> nameList) {
        if (CollectionUtils.isEmpty(nameList)) {
            return Collections.EMPTY_LIST;
        }
        QueryWrapper queryWrapper = WrapperFactory.getQueryWrapper();
        queryWrapper.eq("third_id", thirdId);
        queryWrapper.in("name", nameList);
        return jurisdictionDao.selectList(queryWrapper);
    }

}
