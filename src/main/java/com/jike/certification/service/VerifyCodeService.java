package com.jike.certification.service;

import com.jike.certification.biz.VerifyCodeBiz;
import com.jike.certification.model.verify.VerifyCode;
import com.jike.certification.util.MyAssert;
import com.jike.certification.util.StringUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;
import java.util.Random;

/**
 * @author wentong
 * @date 2019-12-27
 */
@Service
@Slf4j
public class VerifyCodeService {

    @Autowired
    private VerifyCodeBiz verifyCodeBiz;

    @Autowired
    private MailService mailService;

    private Random random = new Random();

    /**
     * 给邮件发送验证码
     *
     * @param mail
     * @return
     */
    public Long sendVerifyCodeByMail(String mail) {
        mailService.checkMail(mail);
        String code = genVerifyCode();
        mailService.sendSimpleMail(mail, "验证码", code);
        VerifyCode lastValidCode = verifyCodeBiz.queryLastValidCode(mail);
        int sendTime = 1;
        if (lastValidCode != null) {
            sendTime = lastValidCode.getSendTimes() + 1;
        }

        VerifyCode verifyCode = VerifyCode.builder()
                                    .code(code)
                                    .sendTimes(sendTime)
                                    .mail(mail)
                                    .build();
        return verifyCodeBiz.save(verifyCode);
    }

    public VerifyCode queryLastValidCode(String mail) {
        return verifyCodeBiz.queryLastValidCode(mail);
    }

    public int update(VerifyCode verifyCode) {
        return verifyCodeBiz.update(verifyCode);
    }

    private String genVerifyCode() {
        return String.valueOf(1000 + random.nextInt(8999));
    }
}
