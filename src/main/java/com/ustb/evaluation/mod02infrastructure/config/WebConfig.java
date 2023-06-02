package com.ustb.evaluation.mod02infrastructure.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class WebConfig implements WebMvcConfigurer {


    @Configuration
    public class WebAppConfigurer extends WebMvcConfigurerAdapter {
        @Override
        public void addCorsMappings(CorsRegistry registry) {
            registry.addMapping("/**")
                    //.allowedOrigins("http://192.168.89.89")
                    .allowedMethods("GET", "POST","DELETE")
                    .allowCredentials(false).maxAge(3600);
        }
    }


//    /**
//     * 跨域配置
//     * @param registry
//     */
//    @Override
//    public void addCorsMappings(CorsRegistry registry) {
//        WebMvcConfigurer.super.addCorsMappings(registry);
//        registry.addMapping("/**") // 访问所有东西都跨域
//                .allowedOrigins("Http://localhost:8080","null")
//                //.allowedHeaders("*")
//                .allowedMethods("POST","GET","PUT","OPTIONS","DELETE") //
//                .maxAge(3600) // 最大响应时间
//                .allowCredentials(true); // 是否携带信息
//    }


}
