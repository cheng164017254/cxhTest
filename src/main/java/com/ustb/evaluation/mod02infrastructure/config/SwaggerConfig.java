package com.ustb.evaluation.mod02infrastructure.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.ArrayList;

@Configuration
public class SwaggerConfig {
    @Bean
    public Docket docket1() {
        return new Docket(DocumentationType.OAS_30)
                .apiInfo(apiInfo1())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.ustb.evaluation.mod03system"))
                .paths(PathSelectors.any())
                .build().groupName("系统管理");
    }

    /**
     * 页面基础信息
     */
    private ApiInfo apiInfo1() {
        // 作者信息
        Contact contact = new Contact("qudegang", "https://www.baidu.com", "65769717@qq.com");
        return new ApiInfo(
                "评估系统的接口文档",
                "系统管理的接口",
                "1.0",
                "https://www.baidu.com/",
                contact,
                "Apache 2.0",
                "http://www.apache.org/licenses/LICENSE-2.0",
                new ArrayList());
    }

    @Bean
    public Docket docket2() {
        return new Docket(DocumentationType.OAS_30)
                .apiInfo(apiInfo2())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.ustb.evaluation.mod05test"))
                .paths(PathSelectors.any())
                .build().groupName("模板代码");

    }

    private ApiInfo apiInfo2() {
        // 作者信息
        Contact contact = new Contact("qudegang", "https://www.baidu.com", "65769717@qq.com");
        return new ApiInfo(
                "模板系统的接口文档",
                "Swagger的测试用的模板",
                "1.0",
                "https://www.baidu.com/",
                contact,
                "Apache 2.0",
                "http://www.apache.org/licenses/LICENSE-2.0",
                new ArrayList());
    }

//    @Bean
//    public Docket docket2() {
//        return new Docket(DocumentationType.OAS_30)
//                .apiInfo(apiInfo2())
//                .select()
//                .apis(RequestHandlerSelectors.basePackage("com.ustb.evaluation.mod04design"))
//                .paths(PathSelectors.any())
//                .build().groupName("设计管理");
//
//    }
//
//    private ApiInfo apiInfo2() {
//        // 作者信息
//        Contact contact = new Contact("qudegang", "https://www.baidu.com", "65769717@qq.com");
//        return new ApiInfo(
//                "评估系统的接口文档",
//                "Swagger的测试用的模板",
//                "1.0",
//                "https://www.baidu.com/",
//                contact,
//                "Apache 2.0",
//                "http://www.apache.org/licenses/LICENSE-2.0",
//                new ArrayList());
//    }

}