package com.jike.certification.biz;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.jike.certification.dao.VerifyCodeDao;
import com.jike.certification.factory.WrapperFactory;
import com.jike.certification.model.verify.VerifyCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author wentong
 * @date 2019-12-27
 */
@Service
public class VerifyCodeBiz {

    @Autowired
    private VerifyCodeDao verifyCodeDao;

    public Long save(VerifyCode verifyCode) {
        int insert = verifyCodeDao.insert(verifyCode);
        return Long.valueOf(insert);
    }

    public Integer update(VerifyCode verifyCode) {
        return verifyCodeDao.updateById(verifyCode);
    }

    /**
     * 查询最后一个有效的验证码
     *
     * @param mail
     * @return
     */
    public VerifyCode queryLastValidCode(String mail) {
        QueryWrapper<VerifyCode> queryWrapper = WrapperFactory.getQueryWrapper();
        queryWrapper.eq("mail",mail);
        queryWrapper.orderByDesc("create_time");
        queryWrapper.last("LIMIT 1");
        return verifyCodeDao.selectOne(queryWrapper);
    }
}
