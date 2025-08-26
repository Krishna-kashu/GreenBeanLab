package com.mywork.onlinelearning.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "com.mywork.onlinelearning")
public class LearningConfiguration implements WebMvcConfigurer {

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

    @Bean
    public MultipartResolver multipartResolver(){
        return new StandardServletMultipartResolver();
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/uploads/**")
                .addResourceLocations("/uploads/");

        registry.addResourceHandler("/images/**")
                .addResourceLocations("/images/");
    }
}
