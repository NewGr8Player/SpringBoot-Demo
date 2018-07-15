package com.xavier.service.impl;

import com.xavier.bean.User;
import com.xavier.dao.UserMapper;
import com.xavier.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * User Service 实现
 *
 * @author NewGr8Player
 */
@Service
@Transactional(readOnly = true)
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public User searchById(String id) {
        return this.userMapper.selectById(id);
    }
}
