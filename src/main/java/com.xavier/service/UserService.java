package com.xavier.service;

import com.xavier.bean.User;

/**
 * User Servicve
 *
 * @author NewGr8Player
 */
public interface UserService {

    /**
     * <p>根据用户Id查询用户信息</p>
     *
     * @param id 用户Id
     * @return User
     */
    User searchById(String id);
}
