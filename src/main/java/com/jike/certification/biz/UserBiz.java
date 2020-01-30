package com.jike.certification.biz;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.jike.certification.commentEnum.DeleteStatus;
import com.jike.certification.dao.UserDao;
import com.jike.certification.factory.WrapperFactory;
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
        return Long.valueOf(userDao.insert(user));
    }

    public User queryByName(String userName){
        QueryWrapper<User> queryWrapper = WrapperFactory.getQueryWrapper();
        queryWrapper.eq("name", userName);
        return userDao.selectOne(queryWrapper);
    }

    public User queryByMail(String mail){
        QueryWrapper<User> queryWrapper = WrapperFactory.getQueryWrapper();
        queryWrapper.eq("mail", mail);
        return userDao.selectOne(queryWrapper);
    }
}
