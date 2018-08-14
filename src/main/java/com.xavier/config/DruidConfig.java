package com.xavier.config;

import com.alibaba.druid.filter.stat.StatFilter;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import com.alibaba.druid.wall.WallConfig;
import com.alibaba.druid.wall.WallFilter;
import com.atomikos.icatch.jta.UserTransactionImp;
import com.atomikos.icatch.jta.UserTransactionManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jta.atomikos.AtomikosDataSourceBean;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;
import org.springframework.transaction.jta.JtaTransactionManager;

import javax.sql.DataSource;
import javax.transaction.UserTransaction;
import java.util.Properties;

/**
 * Druid配置
 *
 * @author NewGr8Player
 */
@Configuration
public class DruidConfig {

	@Primary
	@Autowired
	@Bean(name = "db1")
	public DataSource systemDataSource(Environment env) {
		AtomikosDataSourceBean ds = new AtomikosDataSourceBean();
		Properties prop = build(env, "spring.datasource.druid.systemDB.");
		ds.setXaDataSourceClassName("com.alibaba.druid.pool.xa.DruidXADataSource");
		ds.setUniqueResourceName("db1");
		ds.setPoolSize(5);
		ds.setXaProperties(prop);
		return ds;
	}

	@Autowired
	@Bean(name = "db2")
	public AtomikosDataSourceBean businessDataSource(Environment env) {
		AtomikosDataSourceBean ds = new AtomikosDataSourceBean();
		Properties prop = build(env, "spring.datasource.druid.businessDB.");
		ds.setXaDataSourceClassName("com.alibaba.druid.pool.xa.DruidXADataSource");
		ds.setUniqueResourceName("db2");
		ds.setPoolSize(5);
		ds.setXaProperties(prop);
		return ds;
	}


	/**
	 * 注入事物管理器
	 *
	 * @return
	 */
	@Bean(name = "xatx")
	public JtaTransactionManager regTransactionManager() {
		UserTransactionManager userTransactionManager = new UserTransactionManager();
		UserTransaction userTransaction = new UserTransactionImp();
		return new JtaTransactionManager(userTransaction, userTransactionManager);
	}


	private Properties build(Environment env, String prefix) {

		Properties prop = new Properties();
		prop.put("url", env.getProperty(prefix + "url"));
		prop.put("username", env.getProperty(prefix + "username"));
		prop.put("password", env.getProperty(prefix + "password"));
		prop.put("driverClassName", env.getProperty(prefix + "driverClassName", ""));
		prop.put("initialSize", env.getProperty(prefix + "initialSize", Integer.class));
		prop.put("maxActive", env.getProperty(prefix + "maxActive", Integer.class));
		prop.put("minIdle", env.getProperty(prefix + "minIdle", Integer.class));
		prop.put("maxWait", env.getProperty(prefix + "maxWait", Integer.class));
		prop.put("poolPreparedStatements", env.getProperty(prefix + "poolPreparedStatements", Boolean.class));
		prop.put("maxPoolPreparedStatementPerConnectionSize",
				env.getProperty(prefix + "maxPoolPreparedStatementPerConnectionSize", Integer.class));

		prop.put("maxPoolPreparedStatementPerConnectionSize",
				env.getProperty(prefix + "maxPoolPreparedStatementPerConnectionSize", Integer.class));
		prop.put("validationQuery", env.getProperty(prefix + "validationQuery"));
		prop.put("validationQueryTimeout", env.getProperty(prefix + "validationQueryTimeout", Integer.class));
		prop.put("testOnBorrow", env.getProperty(prefix + "testOnBorrow", Boolean.class));
		prop.put("testOnReturn", env.getProperty(prefix + "testOnReturn", Boolean.class));
		prop.put("testWhileIdle", env.getProperty(prefix + "testWhileIdle", Boolean.class));
		prop.put("timeBetweenEvictionRunsMillis",
				env.getProperty(prefix + "timeBetweenEvictionRunsMillis", Integer.class));
		prop.put("minEvictableIdleTimeMillis", env.getProperty(prefix + "minEvictableIdleTimeMillis", Integer.class));
		prop.put("filters", env.getProperty(prefix + "filters"));

		return prop;
	}


	@Bean
	public ServletRegistrationBean druidServlet() {
		ServletRegistrationBean servletRegistrationBean = new ServletRegistrationBean(new StatViewServlet(), "/druid/*");

		/* durid的登录用户名密码 */
		servletRegistrationBean.addInitParameter("loginUsername", "admin");
		servletRegistrationBean.addInitParameter("loginPassword", "admin");
		return servletRegistrationBean;
	}

	@Bean
	public FilterRegistrationBean filterRegistrationBean() {
		FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
		filterRegistrationBean.setFilter(new WebStatFilter());
		filterRegistrationBean.addUrlPatterns("/*");
		filterRegistrationBean.addInitParameter("exclusions", "*.js,*.gif,*.jpg,*.png,*.css,*.ico,/druid/*");
		filterRegistrationBean.addInitParameter("profileEnable", "true");
		return filterRegistrationBean;
	}

	@Bean
	public StatFilter statFilter() {
		StatFilter statFilter = new StatFilter();
		/* SQL合并配置 */
		statFilter.setMergeSql(true);
		/* 慢SQL时长 */
		statFilter.setSlowSqlMillis(1000);
		/* 慢SQL */
		statFilter.setLogSlowSql(true);
		return statFilter;
	}

	@Bean
	public WallFilter wallFilter() {
		WallFilter wallFilter = new WallFilter();
		/* 允许执行多条SQL */
		WallConfig config = new WallConfig();
		config.setMultiStatementAllow(true);
		wallFilter.setConfig(config);
		return wallFilter;
	}
}
