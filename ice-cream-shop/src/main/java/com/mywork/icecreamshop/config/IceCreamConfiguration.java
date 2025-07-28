package com.mywork.icecreamshop.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = "com.mywork.icecreamshop")
public class IceCreamConfiguration {

    public IceCreamConfiguration(){
        System.out.println("no-arg Constructor of IceCreamConfiguration");
    }
}
