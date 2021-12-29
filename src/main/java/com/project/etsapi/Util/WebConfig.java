package com.project.etsapi.Util;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new TokenInterceptor())
                .addPathPatterns(
                        "/account/**",
                        "/attend/**",
                        "/course/**",
                        "/file/**",
                        "/project/**",
                        "/report/**",
                        "/score/**",
                        "/student/**",
                        "/take/**",
                        "/teach/**",
                        "/teacher/**"
                )
                .excludePathPatterns(
                        "/account/login",
                        "/account/sendEmail",
                        "/account/register",
                        "/test/**"
                );
    }
}
