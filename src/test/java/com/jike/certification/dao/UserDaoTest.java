package com.jike.certification.dao;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.jike.certification.model.user.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@SpringBootTest
@RunWith(SpringRunner.class)
public class UserDaoTest {

    @Autowired
    private UserDao userDao;

    @Test
    public void test(){
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("deleted", 0);
        List<User> users = userDao.selectList(queryWrapper);
        users.forEach(v -> {
            System.out.println(v);
        });
    }
}