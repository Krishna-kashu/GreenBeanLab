package com.mywork.dairy360.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import javax.servlet.MultipartConfigElement;
import javax.servlet.ServletRegistration;
import java.io.File;


@Configuration
@EnableWebMvc
public class DairyInitialization extends AbstractAnnotationConfigDispatcherServletInitializer implements WebMvcConfigurer {

    public DairyInitialization(){
        System.out.println("no-arg constructor of DairyInitialization");
    }


    @Override
    protected String[] getServletMappings() {
        return new String[] {"/"};
    }

    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class[0];
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class[] {DairyConfiguration.class};
    }

    @Override
    protected void customizeRegistration(ServletRegistration.Dynamic registration) {

        System.out.println("\n customizeRegistration in DairyInitialization for file upload");
        File uploadDirectory = new File(System.getProperty("java.io.tmpdir"));

        int maxUploadSizeInMb=5*1024*1024;
        MultipartConfigElement multipartConfigElement =
                new MultipartConfigElement(uploadDirectory.getAbsolutePath(),
                        maxUploadSizeInMb, maxUploadSizeInMb * 2, maxUploadSizeInMb / 2);

        registration.setMultipartConfig(multipartConfigElement);

    }
}
