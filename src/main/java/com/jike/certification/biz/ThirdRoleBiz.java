package com.jike.certification.biz;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jike.certification.commentEnum.DeleteStatus;
import com.jike.certification.dao.ThirdRoleDao;
import com.jike.certification.factory.WrapperFactory;
import com.jike.certification.model.thirdRole.ThirdRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    public ThirdRole selectOne(ThirdRole thirdRole) {
        QueryWrapper<ThirdRole> queryWrapper = WrapperFactory.getQueryWrapper();
        return thirdRole.selectOne(queryWrapper);
    }

    public List<ThirdRole> selectByThirdId(Long thirdId) {
        HashMap<String, Object> map = new HashMap<>();
        map.put("deleted", DeleteStatus.NOT_DELETED.getValue());
        map.put("third_id", thirdId);
        return thirdRoleDao.selectByMap(map);
    }
}
