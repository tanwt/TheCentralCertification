package com.jike.certification.dao;

import com.jike.certification.model.user.User;

/**
 * @author wentong
 * @date 2019-12-26
 */

public interface UserDao {
    Long save(User user);

    User queryByMail(String mail);
}
