package com.mywork.icecreamshop.config;

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
@ComponentScan(basePackages = "com.mywork.icecreamshop")
public class IceCreamConfiguration implements WebMvcConfigurer {

    public IceCreamConfiguration() {
        System.out.println("no-arg Constructor of IceCreamConfiguration");
    }

//    @Override
//    public void addResourceHandlers(ResourceHandlerRegistry registry) {
//        Sy @Override
//    public void addResourceHandlers(ResourceHandlerRegistry registry) {
//        System.out.println("addResourceHandlers method");
//        registry.addResourceHandler("/images/**").addResourceLocations("/images/");
//
//        registry.addResourceHandler("/js/**").addResourceLocations("/js/");
//    }
@Bean
public InternalResourceViewResolver internalResourceViewResolver()
{
    InternalResourceViewResolver internalResourceViewResolver=new InternalResourceViewResolver();

    internalResourceViewResolver.setPrefix("/");
    internalResourceViewResolver.setSuffix(".jsp");

    return internalResourceViewResolver;
}
@Bean
    public MultipartResolver multipartResolver(){
        return new StandardServletMultipartResolver();
}
}
