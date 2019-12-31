package com.jike.certification.dao;

import com.jike.certification.model.user.UserToken;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author wentong
 */
public interface UserTokenDao {

    int save(UserToken userToken);

    int update(UserToken userToken);

    int deleteByUserIdList(@Param("userIdList") List<Long> userIdList);

    int deleteByToken(@Param("token") String token);

    UserToken queryByToken(@Param("token") String token);

    UserToken queryByUserId(@Param("userId") Long userId);

    List<UserToken> queryByUserIdList(@Param("userIdList") List<Long> userIdList);
}
