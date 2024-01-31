package com.min.myblogv1;

import com.min.myblogv1.interceptor.TextValidationInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {

        registry.addInterceptor(new TextValidationInterceptor())
                .addPathPatterns("/image/**")
                .order(1);
    }
}
