package com.jike.certification.biz;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jike.certification.commentEnum.DeleteStatus;
import com.jike.certification.dao.ThirdRoleDao;
import com.jike.certification.factory.WrapperFactory;
import com.jike.certification.model.thirdRole.ThirdRole;
import com.jike.certification.util.CollectionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;

/**
 * @author wentong
 * @date 2020-01-27
 */
@Service
public class ThirdRoleBiz extends ServiceImpl<ThirdRoleDao, ThirdRole> {

    @Autowired
    private ThirdRoleDao thirdRoleDao;

    public ThirdRole selectByThirdIdAndName(Long thirdId, String name) {
        QueryWrapper<ThirdRole> queryWrapper = WrapperFactory.getQueryWrapper();
        queryWrapper.eq("third_id", thirdId);
        queryWrapper.eq("name", name);
        return thirdRoleDao.selectOne(queryWrapper);
    }

    public List<ThirdRole> selectByIdList(List<Long> idList) {
        if (CollectionUtils.isEmpty(idList)) {
            return Collections.EMPTY_LIST;
        }
        QueryWrapper<ThirdRole> queryWrapper = WrapperFactory.getQueryWrapper();
        queryWrapper.in("id", idList);
        return thirdRoleDao.selectList(queryWrapper);
    }

    public List<ThirdRole> selectByThirdIdList(List<Long> thirdIdList) {
        if (CollectionUtils.isEmpty(thirdIdList)) {
            return Collections.EMPTY_LIST;
        }
        QueryWrapper<ThirdRole> queryWrapper = WrapperFactory.getQueryWrapper();
        queryWrapper.in("third_id", thirdIdList);
        return thirdRoleDao.selectList(queryWrapper);
    }

    public List<ThirdRole> selectByThirdId(Long thirdId) {
        HashMap<String, Object> map = new HashMap<>();
        map.put("deleted", DeleteStatus.NOT_DELETED.getValue());
        map.put("third_id", thirdId);
        return thirdRoleDao.selectByMap(map);
    }


}
