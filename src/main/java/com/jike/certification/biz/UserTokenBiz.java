package com.jike.certification.biz;

import com.jike.certification.dao.UserTokenDao;
import com.jike.certification.model.UserToken;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class UserTokenBiz {
    @Autowired
    private UserTokenDao userTokenDao;

    public int save(UserToken userToken) {
        return userTokenDao.save(userToken);
    }

    public int update(UserToken userToken) {
        return userTokenDao.update(userToken);
    }

    public int deleteByUserIdList(List<Long> userIdList) {
        return userTokenDao.deleteByUserIdList(userIdList);
    }

    public int deleteByToken(String token) {
        return userTokenDao.deleteByToken(token);
    }

    public UserToken queryByToken(String token) {
        return userTokenDao.queryByToken(token);
    }

    public UserToken queryByUserId(Long userId) {
        return userTokenDao.queryByUserId(userId);
    }

    public List<UserToken> queryByUserIdList(List<Long> userIdList) {
        return userTokenDao.queryByUserIdList(userIdList);
    }

}
