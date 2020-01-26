package com.jike.certification.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.jike.certification.model.verify.VerifyCode;
import org.apache.ibatis.annotations.Param;

/**
 * @author wentong
 * @date 2019-12-27
 */
public interface VerifyCodeDao extends BaseMapper<VerifyCode> {

    Long save(VerifyCode verifyCode);

    int update(VerifyCode verifyCode);

    VerifyCode queryLastValidCode(@Param("mail") String mail);
}
