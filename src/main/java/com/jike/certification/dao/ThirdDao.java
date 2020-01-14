package com.jike.certification.dao;

import com.jike.certification.model.third.Third;
import com.jike.certification.model.third.ThirdListReq;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author wentong
 * @date 2020-01-13
 */
public interface ThirdDao {

    public Long save(Third third);

    public int update(Third third);

    public Third queryByName(@Param("thirdName") String thirdName);

    /**
     * 通过平台名模糊查询
     * @param thirdName
     * @return
     */
    public List<Third> queryByLikeName(@Param("thirdName") String thirdName);
}
