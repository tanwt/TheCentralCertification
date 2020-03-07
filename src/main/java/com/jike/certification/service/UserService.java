package com.jike.certification.service;

import com.jike.certification.biz.UserBiz;
import com.jike.certification.commentEnum.VerifyCodeEnum;
import com.jike.certification.exception.ApiRuntimeException;
import com.jike.certification.exception.ErrorCode;
import com.jike.certification.model.UserToken;
import com.jike.certification.model.user.*;
import com.jike.certification.model.verify.VerifyCode;
import com.jike.certification.util.CollectionUtil;
import com.jike.certification.util.MyAssert;
import com.jike.certification.util.MyBeanUtils;
import com.jike.certification.util.PageQueryResponse;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.validation.Valid;
import java.util.Collections;
import java.util.List;
import java.util.function.Function;

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
                User user = MyBeanUtils.myCopyProperties(userRegisterReq, new User());
                verifyCode.setStatus(VerifyCodeEnum.VERIFY_SUCCESS.getStatus());
                verifyCode.setVerifyCode(userRegisterReq.getVerifyCode());
                verifyCodeService.update(verifyCode);
                return userBiz.insert(user);
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

    /**
     * 根据用户id 集合查询用户信息
     * @param userIdList
     * @return
     */
    public List<UserVo> queryByUserIdList(List<Long> userIdList) {
        if (CollectionUtils.isEmpty(userIdList)) {
            return Collections.EMPTY_LIST;
        }
        List<User> userList = userBiz.queryByUserIdList(userIdList);
        List<UserVo> userVoList = CollectionUtil.transformList(userList, user -> {
            UserVo userVo = MyBeanUtils.myCopyProperties(user, new UserVo());
            return userVo;
        });
        return userVoList;
    }

    /**
     * 用户信息分页获取
     *
     * @param userPageReq
     * @return
     */
    public PageQueryResponse<UserVo> userPageList(UserPageReq userPageReq){
        PageQueryResponse<User> userPageQueryResponse = userBiz.userPageList(userPageReq);
        return userPageQueryResponse.transform(v -> {
            return MyBeanUtils.myCopyProperties(v, new UserVo());
        });
    }

}
