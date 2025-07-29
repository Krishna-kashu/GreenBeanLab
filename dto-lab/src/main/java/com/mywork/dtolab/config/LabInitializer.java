package com.mywork.dtolab.config;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class LabInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {
    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class[0];
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class[]{LabConfiguration.class};
    }

    @Override
    protected String[] getServletMappings() {
        return new String[] {"/submitVehicle", "/submitBook", "/submitEmployee"};
    }
}
