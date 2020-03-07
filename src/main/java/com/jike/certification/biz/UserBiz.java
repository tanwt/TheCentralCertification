package com.jike.certification.biz;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jike.certification.commentEnum.DeleteStatus;
import com.jike.certification.commentEnum.OrderEnum;
import com.jike.certification.dao.UserDao;
import com.jike.certification.factory.WrapperFactory;
import com.jike.certification.model.Pagination;
import com.jike.certification.model.user.User;
import com.jike.certification.model.user.UserPageReq;
import com.jike.certification.model.user.UserVo;
import com.jike.certification.util.PageQueryResponse;
import com.jike.certification.util.StringUtil;
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

    public PageQueryResponse<User> userPageList(UserPageReq pageReq) {
        boolean isAsc;
        String order = pageReq.getCreateTimeOrder();
        if (StringUtil.checkNotEmpty(order) && order.equals(OrderEnum.ASC.getOrder())) {
            isAsc = true;
        } else {
            isAsc = false;
        }
        QueryWrapper<User> queryWrapper = WrapperFactory.getQueryWrapper();
        if (pageReq.getUserName() != null) {
            queryWrapper.likeLeft("user_name", pageReq.getUserName());
        }
        queryWrapper.orderBy(true, isAsc, "create_time");
        Pagination pagination = pageReq.getPagination();
        Page<User> page = new Page<>(pagination.getPageNum(), pagination.getPageSize());
        return PageQueryResponse.buildPageQueryResponse(userDao.selectPage(page, queryWrapper));
    }
}
