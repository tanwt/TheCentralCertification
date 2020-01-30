package com.jike.certification.biz;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jike.certification.commentEnum.DeleteStatus;
import com.jike.certification.dao.UserTokenDao;
import com.jike.certification.factory.WrapperFactory;
import com.jike.certification.model.UserToken;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class UserTokenBiz extends ServiceImpl<UserTokenDao, UserToken> {
    @Autowired
    private UserTokenDao userTokenDao;

    public int update(UserToken userToken) {
        return userTokenDao.updateById(userToken);
    }

    public boolean deleteByUserIdList(List<Long> userIdList) {
        UpdateWrapper<UserToken> updateWrapper = new UpdateWrapper<>();
        updateWrapper.in("user_id", userIdList);
        updateWrapper.set("deleted", DeleteStatus.DELETED.getValue());
        return update(updateWrapper);
    }

    public boolean deleteByToken(String token) {
        UpdateWrapper<UserToken> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("token", token);
        updateWrapper.set("deleted", DeleteStatus.DELETED.getValue());
        return update(updateWrapper);
    }

    public UserToken queryByToken(String token) {
        QueryWrapper<UserToken> queryWrapper = WrapperFactory.getQueryWrapper();
        queryWrapper.eq("token",token);
        return userTokenDao.selectOne(queryWrapper);
    }

    public UserToken queryByUserId(Long userId) {
        QueryWrapper<UserToken> queryWrapper = WrapperFactory.getQueryWrapper();
        queryWrapper.eq("user_id",userId);
        return userTokenDao.selectOne(queryWrapper);
    }

    public List<UserToken> queryByUserIdList(List<Long> userIdList) {
        QueryWrapper<UserToken> queryWrapper = WrapperFactory.getQueryWrapper();
        queryWrapper.in("user_id",userIdList);
        return userTokenDao.selectList(queryWrapper);
    }

}
