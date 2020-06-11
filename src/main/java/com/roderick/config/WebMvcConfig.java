package com.roderick.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
    @Value("${file.uploadFolder}")
    String uploadFolder;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        //将在项目文件中的图片映射到类路径中
        registry.addResourceHandler("/images/vehicle/**")   //请求的路径
                .addResourceLocations("file:" + uploadFolder);  //文件的实际位置
    }
}
