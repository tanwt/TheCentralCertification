package com.jike.certification.config.redis;

import com.jike.certification.model.UserToken;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public class RedisHandlerTest {

    @Autowired
    private RedisHandler redisHandler;

    @Test
    public void test(){
        System.out.println(redisHandler.get("UserToken_qt6-XwMxDTUBUG4zaCr-wopgYGvHtbc4e_66RRhGwBs", UserToken.class));
    }
}