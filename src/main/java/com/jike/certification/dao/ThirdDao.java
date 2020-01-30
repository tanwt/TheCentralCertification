package com.jike.certification.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jike.certification.model.third.Third;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author wentong
 * @date 2020-01-13
 */
public interface ThirdDao extends BaseMapper<Third> {

    public IPage<Third> thirdList(Page<?> page, String name);
}
