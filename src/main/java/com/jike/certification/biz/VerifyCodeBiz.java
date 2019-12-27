package com.jike.certification.biz;

import com.jike.certification.dao.VerifyCodeDao;
import com.jike.certification.model.verify.VerifyCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author wentong
 * @date 2019-12-27
 */
@Service
public class VerifyCodeBiz {

    @Autowired
    private VerifyCodeDao verifyCodeDao;

    public Long save(VerifyCode verifyCode) {
        verifyCodeDao.save(verifyCode);
        return verifyCode.getId();
    }

    public Integer update(VerifyCode verifyCode) {
        if (verifyCode != null && verifyCode.getId() != null) {
            return verifyCodeDao.update(verifyCode);
        } else {
            return 0;
        }
    }

    /**
     * 查询最后一个有效的验证码
     *
     * @param mail
     * @return
     */
    public VerifyCode queryLastValidCode(String mail) {
        return verifyCodeDao.queryLastValidCode(mail);
    }
}
