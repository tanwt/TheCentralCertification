package com.jike.certification.biz;

import com.jike.certification.dao.UserDao;
import com.jike.certification.model.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author wentong
 * @date 2019-12-26
 */
@Service
public class UserBiz {

    @Autowired
    private UserDao userDao;

    public Long save(User user) {
        return userDao.save(user);
    }

    public User queryByMail(String mail){
        return userDao.queryByMail(mail);
    }
}
