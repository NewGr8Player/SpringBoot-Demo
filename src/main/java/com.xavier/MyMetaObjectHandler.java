package com.xavier;

import com.baomidou.mybatisplus.mapper.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MyMetaObjectHandler extends MetaObjectHandler {

	protected final static Logger logger = LoggerFactory.getLogger(Application.class);

	@Override
	public void insertFill(MetaObject metaObject) {
		logger.info("新增的时候干点不可描述的事情");
		/* 以下是简单的Demo 获取并填值 */
		/*Object lastUpdateNameId = metaObject.getValue("lastUpdateNameId");
		Object lastUpdateTime = metaObject.getValue("lastUpdateTime");
		//获取当前登录用户
		SysUser user = (SysUser)SecurityUtils.getSubject().getPrincipal();
		if (null == lastUpdateNameId) {
			metaObject.setValue("lastUpdateNameId", user.getId());
		}
		if (null == lastUpdateTime) {
			metaObject.setValue("lastUpdateTime", new Date());
		}*/
	}

	@Override
	public void updateFill(MetaObject metaObject) {
		logger.info("更新的时候干点不可描述的事情");
	}
}
