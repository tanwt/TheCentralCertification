package com.jike.certification.service;

import com.jike.certification.biz.JurisdictionBiz;
import com.jike.certification.biz.RoleJurisdictionRelevanceBiz;
import com.jike.certification.biz.ThirdRoleBiz;
import com.jike.certification.commentEnum.DeleteStatus;
import com.jike.certification.model.jurisdiction.Jurisdiction;
import com.jike.certification.model.jurisdiction.JurisdictionVo;
import com.jike.certification.model.jurisdictionGroup.GroupWithJurisdictionVo;
import com.jike.certification.model.jurisdictionGroup.JurisdictionGroupListVo;
import com.jike.certification.model.roleJurisdictionRelevance.RoleJurisdictionRelevance;
import com.jike.certification.model.thirdRole.*;
import com.jike.certification.model.userRoleRelevance.UserRoleRelevanceListVo;
import com.jike.certification.util.CollectionUtil;
import com.jike.certification.util.MyAssert;
import com.jike.certification.util.MyBeanUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

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

    @Autowired
    private JurisdictionGroupService jurisdictionGroupService;

    @Autowired
    private JurisdictionBiz jurisdictionBiz;

    @Autowired
    private RoleJurisdictionRelevanceBiz roleJurisdictionRelevanceBiz;

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
        ThirdRole oldThirdRole = thirdRoleBiz.selectByThirdIdAndName(thirdRoleAddReq.getThirdId(), thirdRoleAddReq.getName());
        if (oldThirdRole == null) {
            ThirdRole thirdRole = MyBeanUtils.myCopyProperties(thirdRoleAddReq, new ThirdRole());
            thirdRoleBiz.save(thirdRole);
            ThirdRole newRole = thirdRoleBiz.getById(thirdRole.getId());
            return MyBeanUtils.myCopyProperties(newRole, new ThirdRoleVo());
        } else {
            return MyBeanUtils.myCopyProperties(oldThirdRole, new ThirdRoleVo());
        }
    }

    /**
     * 更新系统角色信息
     *
     * @param thirdRoleUpdateReq
     * @return
     */
    public ThirdRoleVo updateRole(ThirdRoleUpdateReq thirdRoleUpdateReq) {
        MyAssert.notNull(thirdRoleUpdateReq, "更新信息为空");
        MyAssert.notNull(thirdRoleUpdateReq.getId(), "更新的角色id 不能为空");
        ThirdRole thirdRole = MyBeanUtils.myCopyProperties(thirdRoleUpdateReq, new ThirdRole());
        thirdRole.updateById();
        ThirdRole updateThirdRole = thirdRoleBiz.getById(thirdRole.getId());
        return MyBeanUtils.myCopyProperties(updateThirdRole, new ThirdRoleVo());
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
    public List<ThirdRoleInfoVo> selectByThirdId(Long thirdId) {
        MyAssert.notNull(thirdId, "系统id 不能为空");
        List<ThirdRole> thirdRoleList = thirdRoleBiz.selectByThirdId(thirdId);
        List<UserRoleRelevanceListVo> userRoleRelevanceListVoList = userRoleRelevanceService.userRoleRelevanceList(thirdId);
        Map<Long, List<UserRoleRelevanceListVo>> userRoleRelevanceListMap = CollectionUtil.toMapGroupingBy(userRoleRelevanceListVoList, UserRoleRelevanceListVo::getRoleId);
        List<JurisdictionGroupListVo> jurisdictionGroupListVo = jurisdictionGroupService.getAllGroupByThirdId(thirdId);
        List<Long> groupIdList = CollectionUtil.transformList(jurisdictionGroupListVo, JurisdictionGroupListVo::getId);
        List<Jurisdiction> jurisdictionList = jurisdictionBiz.queryByGroupIdList(groupIdList);
        Map<Long, List<Jurisdiction>> jurisdictionListByGroupId = CollectionUtil.toMapGroupingBy(jurisdictionList, Jurisdiction::getJurisdictionGroupId);
        List<RoleJurisdictionRelevance> roleJurisdictionRelevanceList = roleJurisdictionRelevanceBiz.selectByRoleIdList(CollectionUtil.transformList(thirdRoleList, ThirdRole::getId));
        Map<Long, List<RoleJurisdictionRelevance>> roleJurisdictionRelevanceListMap = CollectionUtil.toMapGroupingBy(roleJurisdictionRelevanceList, RoleJurisdictionRelevance::getRoleId);
        return CollectionUtil.transformList(thirdRoleList, v -> {
            List<RoleJurisdictionRelevance> roleJurisdictionRelevances = roleJurisdictionRelevanceListMap.get(v.getId());
            Map<Long, RoleJurisdictionRelevance> roleJurisdictionRelevanceMap = CollectionUtil.toMap(roleJurisdictionRelevances, RoleJurisdictionRelevance::getJurisdictionId);
            // 构造权限组及其权限
            List<RoleJurisdictionGroupVo> roleJurisdictionGroupVoList = CollectionUtil.transformList(jurisdictionGroupListVo, group -> {
                List<Jurisdiction> jurisdictions = jurisdictionListByGroupId.get(group.getId());
                List<RoleJurisdictionVo> roleJurisdictionVoList = CollectionUtil.transformList(jurisdictions, jurisdiction -> {
                    boolean haveJurisdiction = roleJurisdictionRelevanceMap.containsKey(jurisdiction.getId());
                    return RoleJurisdictionVo.builder()
                               .jurisdictionVo(MyBeanUtils.myCopyProperties(jurisdiction, new JurisdictionVo()))
                               .haveJurisdiction(haveJurisdiction)
                               .relevanceId(haveJurisdiction ? roleJurisdictionRelevanceMap.get(jurisdiction.getId()).getId() : null)
                               .build();
                });
                return RoleJurisdictionGroupVo.builder()
                           .jurisdictionGroupVo(group)
                           .roleJurisdictionVo(roleJurisdictionVoList)
                           .build();
            });


           return ThirdRoleInfoVo.builder()
                .thirdRoleVo(MyBeanUtils.myCopyProperties(v, new ThirdRoleVo()))
                .userRoleRelevanceList(userRoleRelevanceListMap.get(v.getId()))
                .roleJurisdictionGroupVoList(roleJurisdictionGroupVoList)
                .build();
        });
    }
}
