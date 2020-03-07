package com.jike.certification.service;

import com.jike.certification.biz.ThirdBiz;
import com.jike.certification.biz.ThirdRoleBiz;
import com.jike.certification.biz.UserRoleRelevanceBiz;
import com.jike.certification.model.third.*;
import com.jike.certification.model.thirdRole.ThirdRole;
import com.jike.certification.model.thirdRole.ThirdRoleVo;
import com.jike.certification.model.userRoleRelevance.UserRoleRelevance;
import com.jike.certification.model.userRoleRelevance.UserRoleRelevanceInfoVo;
import com.jike.certification.util.CollectionUtil;
import com.jike.certification.util.MyAssert;
import com.jike.certification.util.MyBeanUtils;
import com.jike.certification.util.PageQueryResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

/**
 * @author wentong
 * @date 2020-01-13
 */
@Service
@Slf4j
public class ThirdService {

    @Autowired
    private ThirdBiz thirdBiz;

    @Autowired
    private UserRoleRelevanceBiz userRoleRelevanceBiz;

    @Autowired
    private ThirdRoleBiz thirdRoleBiz;

    /**
     * 新增第三方平台
     *
     * @param third
     * @return
     */
    public Third addThird(Third third) {
        MyAssert.notNull(third, "三方平台新增数据为空");
        MyAssert.notNull(third.getName(), "三方平台名为空");
        Third oldThird = thirdBiz.queryByName(third.getName());
        if (oldThird != null) {
            log.info("已经存在该平台:{}", oldThird);
            return oldThird;
        } else {
            Third newThird = MyBeanUtils.myCopyProperties(third, new Third());
            third.setCreateTime(LocalDateTime.now());
            thirdBiz.save(newThird);
            return newThird;
        }
    }

    public ThirdVo addThird(ThirdAddReq thirdAddReq) {
        MyAssert.notNull(thirdAddReq, "三方平台新增数据为空");
        MyAssert.notNull(thirdAddReq.getName(), "三方平台名为空");
        Third third = addThird(MyBeanUtils.myCopyProperties(thirdAddReq, new Third()));
        return MyBeanUtils.myCopyProperties(third, new ThirdVo());
    }

    /**
     * 更新第三方平台
     *
     * @param third
     * @return
     */
    public ThirdVo updateThird(Third third) {
        MyAssert.notNull(third, "三方平台更新数据为空");
        MyAssert.notNull(third.getId(), "三方平台id 为空");
        String thirdName = third.getName();
        if (thirdName != null) {
            Third oldThird = thirdBiz.queryByName(thirdName);
            MyAssert.isNull(oldThird, "该三方系统已存在");
        }
        thirdBiz.update(third);
        Third updateThird = thirdBiz.getById(third.getId());
        return MyBeanUtils.myCopyProperties(updateThird, new ThirdVo());
    }

    /**
     * 删除第三方平台
     *
     * @param thirdId
     * @return
     */
    public int deleteThirdById(Long thirdId) {
        MyAssert.notNull(thirdId, "删除的平台id 为空");
        return thirdBiz.deleteThirdById(thirdId);
    }

    public List<ThirdVo> queryAllThird() {
        List<Third> thirdList = thirdBiz.queryAllThird();
        return CollectionUtil.transformList(thirdList, third -> MyBeanUtils.myCopyProperties(third, new ThirdVo()));
    }

    /**
     * 分页请求数据
     *
     * @param thirdPageReq
     * @return
     */
    public PageQueryResponse<ThirdPageVo> thirdList(ThirdPageReq thirdPageReq) {
        MyAssert.notNull(thirdPageReq, "三方平台分页请求数据为空");
        PageQueryResponse<Third> thirdPageQueryResponse = thirdBiz.thirdList(thirdPageReq);
        return thirdPageQueryResponse.transform(third -> {
            return MyBeanUtils.myCopyProperties(third, new ThirdPageVo());
        });
    }

    /**
     * 获取用户在各个系统的信息
     *
     * @param userId
     * @return
     */
    public List<UserThirdInfoVo> getUserThirdInfo(Long userId) {
        MyAssert.notNull(userId, "查用用户系统信息：userId 为空");
        List<ThirdVo> allThird = queryAllThird();
        List<ThirdRole> allRoleList = thirdRoleBiz.selectByThirdIdList(CollectionUtil.transformList(allThird, ThirdVo::getId));
        Map<Long, List<ThirdRole>> roleByThirdIdMap = CollectionUtil.toMapGroupingBy(allRoleList, ThirdRole::getThirdId);
        List<UserRoleRelevance> userRoleRelList = userRoleRelevanceBiz.queryByUserId(userId);
        Map<Long, List<UserRoleRelevance>> userRoleRelByThirdIdMap = CollectionUtil.toMapGroupingBy(userRoleRelList, UserRoleRelevance::getThirdId);
        return CollectionUtil.transformList(allThird, thirdVo -> {
            List<ThirdRole> thirdRoleList = roleByThirdIdMap.get(thirdVo.getId());
            List<UserRoleRelevance> userRoleRelevanceList = userRoleRelByThirdIdMap.get(thirdVo.getId());
            Map<Long, UserRoleRelevance> relevanceMap = CollectionUtil.toMap(userRoleRelevanceList, UserRoleRelevance::getRoleId);
            List<UserRoleRelevanceInfoVo> userRoleRelevanceInfoVoList = CollectionUtil.transformList(thirdRoleList, thirdRole -> {
                UserRoleRelevance userRoleRelevance = relevanceMap.get(thirdRole.getId());
                return UserRoleRelevanceInfoVo.builder()
                           .thirdRoleVo(MyBeanUtils.myCopyProperties(thirdRole, new ThirdRoleVo()))
                           .relevanceId(userRoleRelevance == null ? null : userRoleRelevance.getId())
                           .haveRole(userRoleRelevance != null)
                           .build();
            });
            return UserThirdInfoVo.builder()
                       .thirdVo(thirdVo)
                       .userRoleRelevanceInfoVoList(userRoleRelevanceInfoVoList)
                       .build();
        });
    }
}
