package com.mywork.usermanagement.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@Configuration
@ComponentScan(basePackages = "com.mywork.usermanagement")
public class UserConfiguration {
    public UserConfiguration(){
        System.out.println("no-arg constructor of UserConfiguration");
    }

    @Bean
    InternalResourceViewResolver internalResourceViewResolver(){
        InternalResourceViewResolver resourceViewResolver = new InternalResourceViewResolver();
        resourceViewResolver.setPrefix("/");
        resourceViewResolver.setSuffix(".jsp");

        return resourceViewResolver;
    }
}
