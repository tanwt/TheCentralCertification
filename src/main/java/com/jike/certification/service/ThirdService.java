package com.jike.certification.service;

import com.jike.certification.biz.ThirdBiz;
import com.jike.certification.model.third.Third;
import com.jike.certification.model.third.ThirdListReq;
import com.jike.certification.model.third.ThirdListVo;
import com.jike.certification.util.MyAssert;
import com.jike.certification.util.MyBeanUtils;
import com.jike.certification.util.PageQueryResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
            thirdBiz.save(newThird);
            return newThird;
        }
    }

    /**
     * 更新第三方平台
     *
     * @param third
     * @return
     */
    public int updateThird(Third third) {
        MyAssert.notNull(third, "三方平台更新数据为空");
        MyAssert.notNull(third.getId(), "三方平台id 为空");
        return thirdBiz.update(third);
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

    /**
     * 分页请求数据
     *
     * @param thirdListReq
     * @return
     */
    public PageQueryResponse<ThirdListVo> thirdList(ThirdListReq thirdListReq) {
        MyAssert.notNull(thirdListReq, "三方平台分页请求数据为空");
        PageQueryResponse<Third> thirdPageQueryResponse = thirdBiz.thirdList(thirdListReq);
        return thirdPageQueryResponse.transform(third -> {
            return MyBeanUtils.myCopyProperties(third, new ThirdListVo());
        });
    }
}
