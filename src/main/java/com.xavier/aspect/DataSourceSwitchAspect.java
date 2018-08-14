package com.xavier.aspect;

import com.xavier.common.db.DBTypeEnum;
import com.xavier.common.db.DataSourceSwitch;
import com.xavier.common.db.DbContextHolder;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.Objects;

/**
 * 动态切换数据源
 *
 * @author NewGr8Player
 */
@Aspect
@Order(-100)
@Component
public class DataSourceSwitchAspect {

	private static final Logger logger = LoggerFactory.getLogger(DataSourceSwitchAspect.class);

	@Pointcut("execution(* com.xavier.service.impl..*.*(..))")
	private void dbAspect() {
	}

	/**
	 * 添加注解方式
	 * 如果有注解优先注解
	 * 没有则使用指定数据源
	 *
	 * @param joinPoint
	 */
	@Before("dbAspect()")
	public void setDataSource(JoinPoint joinPoint) {
		MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
		DataSourceSwitch dataSourceSwitch = methodSignature.getMethod().getAnnotation(DataSourceSwitch.class);
		if (Objects.isNull(dataSourceSwitch) || Objects.isNull(dataSourceSwitch.value())) {
			DbContextHolder.setDbType(DBTypeEnum.db1);
		} else {
			logger.info("注解来切换数据源,注解值为:" + dataSourceSwitch.value());
			DbContextHolder.setDbType(dataSourceSwitch.value());
		}
		logger.info("使用数据源：" + DbContextHolder.getDbType());
	}
}
