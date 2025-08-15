package com.mywork.toaster_tune.config;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import javax.servlet.MultipartConfigElement;
import javax.servlet.ServletRegistration;
import java.io.File;

public class ToasterInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {
    private static final String TEMP_FOLDER = "D:/MyFilesJava";
    private static final int MAX_UPLOAD_SIZE = 5 * 1024 * 1024;

    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class[0];
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class[]{ToasterConfiguration.class};
    }

    @Override
    protected String[] getServletMappings() {
        return new String[]{"/"};
    }

    @Override
    protected void customizeRegistration(ServletRegistration.Dynamic registration) {
        File uploadDir = new File(TEMP_FOLDER);
        if (!uploadDir.exists()) {
            uploadDir.mkdirs();
        }

        MultipartConfigElement multipartConfigElement =
                new MultipartConfigElement(
                        uploadDir.getAbsolutePath(),
                        MAX_UPLOAD_SIZE,
                        MAX_UPLOAD_SIZE * 2,
                        MAX_UPLOAD_SIZE / 2
                );
        registration.setMultipartConfig(multipartConfigElement);
    }
}
