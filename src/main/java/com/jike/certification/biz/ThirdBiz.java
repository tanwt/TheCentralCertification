package com.jike.certification.biz;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jike.certification.dao.ThirdDao;
import com.jike.certification.factory.WrapperFactory;
import com.jike.certification.model.Pagination;
import com.jike.certification.model.third.Third;
import com.jike.certification.model.third.ThirdPageReq;
import com.jike.certification.util.PageQueryResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author wentong
 * @date 2020-01-13
 */
@Service
public class ThirdBiz {

    @Autowired
    private ThirdDao thirdDao;

    public Long save(Third third) {
        return Long.valueOf(thirdDao.insert(third));
    }

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

    public PageQueryResponse<Third> thirdList(ThirdPageReq thirdPageReq){
        QueryWrapper<Third> queryWrapper = WrapperFactory.getQueryWrapper();
        queryWrapper.likeLeft("name", thirdPageReq.getName());
        Pagination pagination = thirdPageReq.getPagination();
        Page<Third> page = new Page<>(pagination.getPageNum(), pagination.getPageSize());
        return PageQueryResponse.buildPageQueryResponse(thirdDao.selectPage(page, queryWrapper));
    }
}