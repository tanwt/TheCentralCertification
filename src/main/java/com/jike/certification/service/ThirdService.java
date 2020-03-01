package com.jike.certification.service;

import com.jike.certification.biz.ThirdBiz;
import com.jike.certification.model.third.*;
import com.jike.certification.util.CollectionUtil;
import com.jike.certification.util.MyAssert;
import com.jike.certification.util.MyBeanUtils;
import com.jike.certification.util.PageQueryResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author wentong
 * @date 2020-01-13
 */
@Service
@Slf4j
public class ThirdService {

    @Autowired
    private ThirdBiz thirdBiz;

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
}
