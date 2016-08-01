package com.peaceful.demo.spring.boot.common;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * @author WangJun
 * @version 1.0 16/7/23
 */
@Configuration
public class ConfigDemo2 {

    @Bean
    public ConfigDemo getConfigDemo(){
        return new ConfigDemo();
    }


}
