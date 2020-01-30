package com.jike.certification.service;

import com.jike.certification.biz.ThirdRoleBiz;
import com.jike.certification.commentEnum.DeleteStatus;
import com.jike.certification.model.thirdRole.ThirdRole;
import com.jike.certification.model.thirdRole.ThirdRoleAddReq;
import com.jike.certification.model.thirdRole.ThirdRoleUpdateReq;
import com.jike.certification.model.thirdRole.ThirdRoleVo;
import com.jike.certification.util.CollectionUtil;
import com.jike.certification.util.MyAssert;
import com.jike.certification.util.MyBeanUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author wentong
 * @date 2020-01-27
 */
@Service
@Slf4j
public class ThirdRoleService {

    @Autowired
    private ThirdRoleBiz thirdRoleBiz;

    @Autowired
    private UserRoleRelevanceService userRoleRelevanceService;

    /**
     * 新增系统角色
     * 同一系统不能有相同角色
     *
     * @param thirdRoleAddReq
     * @return
     */
    public ThirdRoleVo addRole(ThirdRoleAddReq thirdRoleAddReq) {
        MyAssert.notNull(thirdRoleAddReq, "系统角色新增信息为空");
        MyAssert.notNull(thirdRoleAddReq.getThirdId(), "系统id为空");
        MyAssert.notNull(thirdRoleAddReq.getName(), "角色名为空");
        ThirdRole thirdRole = MyBeanUtils.myCopyProperties(thirdRoleAddReq, new ThirdRole());
        ThirdRole oldThirdRole = thirdRoleBiz.selectOne(thirdRole);
        MyAssert.isNull(oldThirdRole, "该系统已存在该角色");
        Long save = thirdRoleBiz.save(thirdRole);
        return MyBeanUtils.myCopyProperties(thirdRole, new ThirdRoleVo());
    }

    /**
     * 更新系统角色信息
     *
     * @param thirdRoleUpdateReq
     * @return
     */
    public boolean updateRole(ThirdRoleUpdateReq thirdRoleUpdateReq) {
        MyAssert.notNull(thirdRoleUpdateReq, "更新信息为空");
        MyAssert.notNull(thirdRoleUpdateReq.getId(), "更新的角色id 不能为空");
        ThirdRole thirdRole = MyBeanUtils.myCopyProperties(thirdRoleUpdateReq, new ThirdRole());
        return thirdRole.updateById();
    }

    /**
     * 删除系统角色
     * 同时删除角色用户关联
     *
     * @param roleId
     * @return
     */
    @Transactional(rollbackFor=Exception.class)
    public boolean deletedRole(Long roleId) {
        MyAssert.notNull(roleId, "删除的系统角色id 为空");
        ThirdRole thirdRole = ThirdRole.builder()
                                  .id(roleId)
                                  .deleted(DeleteStatus.DELETED.getValue())
                                  .build();
        boolean flag = false;
        flag = thirdRole.updateById();
        flag = userRoleRelevanceService.deletedByRoleId(roleId);
        return false;
    }

    /**
     * 查询一个系统中的所有角色
     *
     * @param thirdId
     * @return
     */
    public List<ThirdRoleVo> selectByThirdId(Long thirdId) {
        MyAssert.notNull(thirdId, "系统id 不能为空");
        List<ThirdRole> thirdRoleList = thirdRoleBiz.selectByThirdId(thirdId);
        return CollectionUtil.transformList(thirdRoleList, v -> {
            return MyBeanUtils.myCopyProperties(v, new ThirdRoleVo());
        });
    }
}
