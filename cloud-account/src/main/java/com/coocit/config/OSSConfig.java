package com.coocit.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author: Coocit
 * @date: 2024/3/7
 * @description:
 */
@ConfigurationProperties(prefix = "aliyun.oss")
@Configuration
@Data
public class OSSConfig {

    private String endpoint;
    private String accessKeyId;
    private String accessKeySecret;
    private String bucketname;

}
