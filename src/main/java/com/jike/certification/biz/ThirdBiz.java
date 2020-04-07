package com.jike.certification.biz;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jike.certification.commentEnum.OrderEnum;
import com.jike.certification.dao.ThirdDao;
import com.jike.certification.factory.WrapperFactory;
import com.jike.certification.model.Pagination;
import com.jike.certification.model.third.Third;
import com.jike.certification.model.third.ThirdPageReq;
import com.jike.certification.util.PageQueryResponse;
import com.jike.certification.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author wentong
 * @date 2020-01-13
 */
@Service
public class ThirdBiz extends ServiceImpl<ThirdDao, Third> {

    @Autowired
    private ThirdDao thirdDao;

    public int update(Third third) {
        return thirdDao.updateById(third);
    }

    public int deleteThirdById(Long thirdId) {
        return thirdDao.deleteById(thirdId);
    }

    public Third queryByName(String thirdName) {
        QueryWrapper<Third> queryWrapper = WrapperFactory.getQueryWrapper();
        queryWrapper.eq("name", thirdName);
        return thirdDao.selectOne(queryWrapper);
    }

    public List<Third> queryAllThird(){
        QueryWrapper<Third> queryWrapper = WrapperFactory.getQueryWrapper();
        return thirdDao.selectList(queryWrapper);
    }

    public PageQueryResponse<Third> thirdList(ThirdPageReq thirdPageReq){
        boolean isAsc;
        String orderByUpdateTime = thirdPageReq.getOrderByUpdateTime();
        if (StringUtil.checkNotEmpty(orderByUpdateTime) && orderByUpdateTime.equals(OrderEnum.ASC.getOrder())) {
            isAsc = true;
        } else {
            isAsc = false;
        }
        QueryWrapper<Third> queryWrapper = WrapperFactory.getQueryWrapper();
        if (StringUtil.checkNotEmpty(thirdPageReq.getName())) {
            queryWrapper.like("name", thirdPageReq.getName());
        }
        queryWrapper.orderBy(true, isAsc, "update_time");
        Pagination pagination = thirdPageReq.getPagination();
        Page<Third> page = new Page<>(pagination.getPageNum(), pagination.getPageSize());
        return PageQueryResponse.buildPageQueryResponse(thirdDao.selectPage(page, queryWrapper));
    }
}
