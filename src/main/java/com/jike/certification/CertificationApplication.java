package com.jike.certification;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author wentong
 */
@EnableCaching
@EnableAsync(proxyTargetClass = true)
@EnableScheduling
@SpringBootApplication
@EnableTransactionManagement
@MapperScan("com.jike.certification.dao")
public class CertificationApplication {

    public static void main(String[] args) {
        SpringApplication.run(CertificationApplication.class, args);
    }

}
