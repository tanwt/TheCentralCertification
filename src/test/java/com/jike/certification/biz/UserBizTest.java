package com.jike.certification.biz;

import com.jike.certification.dao.UserDao;
import com.jike.certification.model.user.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@SpringBootTest
@RunWith(SpringRunner.class)
public class UserBizTest {

    @Autowired
    private UserBiz userBiz;

    @Test
    public void queryByName() {
        User user = userBiz.queryByName("wt");
        System.out.println(user);
    }
}