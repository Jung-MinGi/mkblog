package com.min.myblogv1;

import com.min.myblogv1.interceptor.LoginValidationInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {

        registry.addInterceptor(new LoginValidationInterceptor())
                .addPathPatterns("/update/**")
                .order(1);
    }
}
