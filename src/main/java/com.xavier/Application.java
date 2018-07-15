package com.xavier;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

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
@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
