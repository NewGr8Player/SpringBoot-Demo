package com.xavier;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * <ul >
 * <li ><input checked type="checkbox">整合Druid连接池</li>
 * <li ><input checked type="checkbox">整合MyBatisPlus</li>
 * <li ><input checked type="checkbox">整合Shiro与JWT</li>
 * <li ><input checked type="checkbox">整合Redis</li>
 * </ul>
 *
 * @author NewGr8Player
 */
@EnableCaching
@SpringBootApplication(exclude = {
        DataSourceAutoConfiguration.class
})
@EnableTransactionManagement(order = 2)
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
