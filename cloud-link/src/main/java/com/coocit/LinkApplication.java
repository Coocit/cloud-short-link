package com.coocit;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author: Coocit
 * @date: 2024/3/20
 * @description:
 */
@MapperScan("com.coocit.mapper")
@EnableTransactionManagement
@EnableFeignClients
@EnableDiscoveryClient
@SpringBootApplication
public class LinkApplication {

    public static void main(String[] args) {
        SpringApplication.run(LinkApplication.class, args);
    }

}
