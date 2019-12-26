package com.jike.certification.service;

import com.jike.certification.biz.UserBiz;
import com.jike.certification.model.User;
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

    public Long addUser(@Valid User user) {
        userBiz.save(user);
        return user.getId();
    }
}
