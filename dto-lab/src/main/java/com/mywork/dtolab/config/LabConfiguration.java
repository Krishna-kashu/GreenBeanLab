package com.mywork.dtolab.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = "com.mywork.dtolab")
public class LabConfiguration
{
    public LabConfiguration(){
        System.out.println("no-arg constructor of LabConfiguration ");
    }
}
