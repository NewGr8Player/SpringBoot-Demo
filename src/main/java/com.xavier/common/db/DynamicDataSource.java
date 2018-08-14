package com.xavier.common.db;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * 动态切换数据源
 *
 * @author NewGr8Player
 */
public class DynamicDataSource extends AbstractRoutingDataSource {
	/**
	 * 取得当前使用哪个数据源
	 *
	 * @return
	 */
	@Override
	protected Object determineCurrentLookupKey() {
		return DbContextHolder.getDbType();
	}
}
