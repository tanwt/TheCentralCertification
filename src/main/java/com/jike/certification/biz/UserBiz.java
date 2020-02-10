package com.jike.certification.biz;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jike.certification.commentEnum.DeleteStatus;
import com.jike.certification.dao.UserDao;
import com.jike.certification.factory.WrapperFactory;
import com.jike.certification.model.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author wentong
 * @date 2019-12-26
 */
@Service
public class UserBiz extends ServiceImpl<UserDao, User> {

    @Autowired
    private UserDao userDao;

    public Long insert(User user) {
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

    public List<User> queryByUserIdList(List<Long> userIdList) {
        QueryWrapper<User> queryWrapper = WrapperFactory.getQueryWrapper();
        queryWrapper.in("id", userIdList);
        return userDao.selectList(queryWrapper);
    }
}
