package com.mywork.contact_app.config;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = "com.mywork.contact_app")
public class ContactConfiguration {
    public ContactConfiguration(){
        System.out.println("no-arg constructor of ContactConfiguration");
    }
}