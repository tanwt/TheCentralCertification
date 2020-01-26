package com.jike.certification.service;

import com.jike.certification.biz.UserBiz;
import com.jike.certification.commentEnum.VerifyCodeEnum;
import com.jike.certification.exception.ApiRuntimeException;
import com.jike.certification.exception.ErrorCode;
import com.jike.certification.model.UserToken;
import com.jike.certification.model.user.*;
import com.jike.certification.model.verify.VerifyCode;
import com.jike.certification.util.MyAssert;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    @Autowired
    private UserTokenService userTokenService;

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
        MailService.checkMail(userRegisterReq.getMail());
        VerifyCode verifyCode = verifyCodeService.queryLastValidCode(userRegisterReq.getMail());
        MyAssert.notNull(verifyCode, "验证失效或邮箱输入错误，请检查邮箱或重新获取验证码");
        // 判断验证码是否对等
        if (userRegisterReq.getVerifyCode().equals(verifyCode.getCode())) {
            User oldUser = userBiz.queryByMail(userRegisterReq.getMail());
            // 判断邮箱有没有重复
            if (oldUser != null && oldUser.getMail().equals(userRegisterReq.getMail())){
                verifyCode.setStatus(VerifyCodeEnum.VERIFY_FAIL.getStatus());
                verifyCodeService.update(verifyCode);
                verifyCode.setVerifyCode(userRegisterReq.getVerifyCode());
                throw new ApiRuntimeException(ErrorCode.USER_ACCOUNT_MAIL_DUPLICATE);
            } else {
                User user = new User();
                BeanUtils.copyProperties(userRegisterReq, user);
                verifyCode.setStatus(VerifyCodeEnum.VERIFY_SUCCESS.getStatus());
                verifyCode.setVerifyCode(userRegisterReq.getVerifyCode());
                verifyCodeService.update(verifyCode);
                return userBiz.save(user);
            }
        } else {
            throw new ApiRuntimeException(ErrorCode.SYSTEM_VERIFY_CODE_ERROR);
        }
    }

    /**
     * 用户通过邮箱登陆
     *
     * @param userLoginReq
     * @return
     */
    public UserToken userLoginByMail(UserLoginReq userLoginReq) {
        MailService.checkMail(userLoginReq.getMail());
        User user = userBiz.queryByMail(userLoginReq.getMail());
        MyAssert.notNull(user, "该邮箱未注册");
        MyAssert.isTrue(user.getPassword().equals(userLoginReq.getPassword()), "密码错误");
        UserToken userToken = UserToken.builder()
                                  .thirdId(userLoginReq.getThirdId())
                                  .userId(user.getId())
                                  .build();
        userTokenService.setOrFlushToken(userToken);
        return userToken;
    }
}
