package com.jike.certification.service;

import com.jike.certification.biz.UserBiz;
import com.jike.certification.commentEnum.VerifyCodeEnum;
import com.jike.certification.exception.ApiRuntimeException;
import com.jike.certification.exception.ErrorCode;
import com.jike.certification.model.user.User;
import com.jike.certification.model.user.UserRegisterReq;
import com.jike.certification.model.verify.VerifyCode;
import com.jike.certification.util.MyAssert;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;

/**
 * @author wentong
 * @date 2019-12-26
 */
@Service
public class UserService {

    @Autowired
    private UserBiz userBiz;
    @Autowired
    private VerifyCodeService verifyCodeService;
    @Autowired
    private MailService mailService;

    public Long addUser(@Valid User user) {
        userBiz.save(user);
        return user.getId();
    }

    /**
     * 用户注册
     * 判断验证码是否对等
     * 判断邮箱有没有重复
     *
     * @param userRegisterReq
     * @return
     */
    public Long userRegister(UserRegisterReq userRegisterReq) {
        mailService.checkMail(userRegisterReq.getMail());
        VerifyCode verifyCode = verifyCodeService.queryLastValidCode(userRegisterReq.getMail());
        MyAssert.notNull(verifyCode, "验证失效或邮箱输入错误，请检查邮箱或重新获取验证码");
        // 判断验证码是否对等
        if (userRegisterReq.getVerifyCode().equals(verifyCode.getVerifyCode())) {
            User oldUser = userBiz.queryByMail(userRegisterReq.getMail());
            // 判断邮箱有没有重复
            if (oldUser != null && oldUser.getMail().equals(userRegisterReq.getMail())){
                verifyCode.setStatus(VerifyCodeEnum.VERIFY_FAIL.getStatus());
                verifyCodeService.update(verifyCode);
                throw new ApiRuntimeException(ErrorCode.USER_ACCOUNT_MAIL_DUPLICATE);
            } else {
                User user = new User();
                BeanUtils.copyProperties(userRegisterReq, user);
                verifyCode.setStatus(VerifyCodeEnum.VERIFY_SUCCESS.getStatus());
                verifyCodeService.update(verifyCode);
                return userBiz.save(user);
            }
        } else {
            throw new ApiRuntimeException(ErrorCode.SYSTEM_VERIFY_CODE_ERROR);
        }
    }
}
