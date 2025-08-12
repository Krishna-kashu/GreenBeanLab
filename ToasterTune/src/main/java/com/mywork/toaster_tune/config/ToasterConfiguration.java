package com.mywork.toaster_tune.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@Configuration
@EnableWebMvc
@ComponentScan("com.mywork.toaster_tune")
public class ToasterConfiguration implements WebMvcConfigurer {
    public ToasterConfiguration() {
        System.out.println("no-arg Constructor of ToasterConfiguration");
    }

    @Bean
    public InternalResourceViewResolver internalResourceViewResolver(){
        InternalResourceViewResolver resourceViewResolver = new InternalResourceViewResolver();

        resourceViewResolver.setPrefix("/");
        resourceViewResolver.setSuffix(".jsp");

        return  resourceViewResolver;
    }

    @Bean
    public MultipartResolver multipartResolver()
    {
        return  new StandardServletMultipartResolver();
    }
}
