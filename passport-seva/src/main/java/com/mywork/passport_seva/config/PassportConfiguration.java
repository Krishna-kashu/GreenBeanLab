package com.mywork.passport_seva.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@Configuration
@ComponentScan(basePackages = "com.mywork.passport_seva")
public class PassportConfiguration {
    public PassportConfiguration(){
        System.out.println("PassportConfiguration constructor");
    }

    @Bean
    public InternalResourceViewResolver viewResolver(){
        InternalResourceViewResolver resolver = new InternalResourceViewResolver();

        resolver.setPrefix("/");
        resolver.setSuffix(".jsp");

        return  resolver ;
    }
}
