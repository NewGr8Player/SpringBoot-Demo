package com.xavier.service.impl;

import com.xavier.bean.User;
import com.xavier.common.db.DBTypeEnum;
import com.xavier.common.db.DataSourceSwitch;
import com.xavier.dao.UserMapper;
import com.xavier.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


/**
 * User Service 实现
 * <br />
 * 事务注解中事务管理器名称{@link com.xavier.config.DruidConfig#regTransactionManager}上的注解
 *
 * @author NewGr8Player
 */
@Service
@Transactional(readOnly = true, transactionManager = "xatx", propagation = Propagation.REQUIRED, rollbackFor = {java.lang.Exception.class})
public class UserServiceImpl implements UserService {

	@Autowired
	private UserMapper userMapper;

	//@DataSourceSwitch(DBTypeEnum.db2)
	@Override
	public User searchById(String id) {
		return this.userMapper.selectById(id);
	}
}
