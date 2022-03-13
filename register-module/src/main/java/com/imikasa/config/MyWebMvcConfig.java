package com.imikasa.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
public class MyWebMvcConfig implements WebMvcConfigurer {

    @Value("${uploadImgPath}")
    private String uploadPath;
    //映射文件路径到url
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/uploadview/**").addResourceLocations("file:"+uploadPath);
    }
}