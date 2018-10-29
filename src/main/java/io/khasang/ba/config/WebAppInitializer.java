package io.khasang.ba.config;

import io.khasang.ba.config.application.WebConfig;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import javax.servlet.MultipartConfigElement;
import javax.servlet.ServletRegistration;

// web.xml
public class WebAppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {
    private static final int MAX_UPLOAD_SIZE = 50 * 1024 * 1024;
    private static final int MAX_REQUEST_SIZE = MAX_UPLOAD_SIZE + (MAX_UPLOAD_SIZE >> 1);
    private static final int FILE_SIZE_THRESHOLD = MAX_UPLOAD_SIZE >> 1;

    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class[]{WebConfig.class};
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        return null;
    }

    @Override
    protected String[] getServletMappings() {
        return new String[]{"/"};
    }

    @Override
    protected void customizeRegistration(ServletRegistration.Dynamic registration) {
        registration.setMultipartConfig(
                new MultipartConfigElement(
                        null,
                        MAX_UPLOAD_SIZE,
                        MAX_REQUEST_SIZE,
                        FILE_SIZE_THRESHOLD));
    }
}
