package com.jike.certification.service;

import com.jike.certification.util.MyAssert;
import com.jike.certification.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

/**
 * @author wentong
 * @date 2019-12-27
 */
@Service
public class MailService {
    @Autowired
    private JavaMailSender mailSender;

    @Value("${spring.mail.username}")
    private String from;

    public void sendSimpleMail(String to, String subject, String content) {
        checkMail(to);
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(from);
        message.setTo(to);
        message.setSubject(subject);
        message.setText(content);

        mailSender.send(message);
    }

    public void checkMail(String mail){
        MyAssert.notNull(mail, "邮件不能为空");
        MyAssert.isTrue(StringUtil.checkEmail(mail), "邮件格式有误");
    }
}
