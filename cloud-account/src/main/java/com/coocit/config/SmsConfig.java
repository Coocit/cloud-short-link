package com.coocit.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author: Coocit
 * @date: 2024/2/25
 * @description:
 */
@ConfigurationProperties(prefix = "sms")
@Configuration
@Data
public class SmsConfig {

    private String templateId;

    private String appCode;

}
