package com.jike.certification.biz;

import com.github.pagehelper.PageHelper;
import com.jike.certification.commentEnum.DeleteStatus;
import com.jike.certification.constants.CommonConstants;
import com.jike.certification.dao.ThirdDao;
import com.jike.certification.model.third.Third;
import com.jike.certification.model.third.ThirdListReq;
import com.jike.certification.util.MyAssert;
import com.jike.certification.util.PageQueryResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author wentong
 * @date 2020-01-13
 */
@Service
@Slf4j
public class ThirdBiz {

    @Autowired
    private ThirdDao thirdDao;

    public Long save(Third third) {
        return thirdDao.save(third);
    }

    public int update(Third third) {
        return thirdDao.update(third);
    }

    public int deleteThirdById(Long thirdId) {
        // 删除需要在biz 层做检测
        MyAssert.notNull(thirdId, "删除的平台id 为空");
        Third third = Third.builder()
                          .id(thirdId)
                          .deleted(DeleteStatus.DELETED.getValue())
                          .build();
        return thirdDao.update(third);
    }

    public Third queryByName(String thirdName) {
        return thirdDao.queryByName(thirdName);
    }

    public PageQueryResponse<Third> thirdList(ThirdListReq thirdListReq) {
        return PageQueryResponse.buildFromPageHelperSupplier(thirdListReq.getPagination(), CommonConstants.MAX_PAGE_SIZE, pagination -> {
            PageHelper.startPage(thirdListReq.getPagination().getPageNum(), thirdListReq.getPagination().getPageSize());
            return thirdDao.queryByLikeName(thirdListReq.getName());
        });
    }
}
