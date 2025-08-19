package com.mywork.onlinelearning.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@Configuration
@ComponentScan(basePackages = "com.mywork.onlinelearning")
public class LearningConfiguration {

    public LearningConfiguration() {
        System.out.println("no-arg constructor in LearningConfiguration");
    }

    @Bean
    public InternalResourceViewResolver resourceViewResolver (){
        InternalResourceViewResolver resolver = new InternalResourceViewResolver();
        resolver.setPrefix("/");
        resolver.setSuffix(".jsp");
        return  resolver;
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder(){
        System.out.println("bCryptPasswordEncoder method in configuration");
        return new BCryptPasswordEncoder();
    }
}
