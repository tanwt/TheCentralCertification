//package com.jike.system1.config;
//
//import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import springfox.documentation.builders.ApiInfoBuilder;
//import springfox.documentation.builders.PathSelectors;
//import springfox.documentation.builders.RequestHandlerSelectors;
//import springfox.documentation.service.ApiInfo;
//import springfox.documentation.spi.DocumentationType;
//import springfox.documentation.spring.web.plugins.Docket;
//import springfox.documentation.swagger2.annotations.EnableSwagger2;
//
//
///**
// * @author wentong
// */
//@Configuration
//@EnableSwagger2
//@ConditionalOnExpression("${swagger.enable}")
//public class SwaggerConfig {
//    @Bean
//    public Docket createRestApi() {
//        return new Docket(DocumentationType.SWAGGER_2)
//                   .apiInfo(apiInfo())
//                   .select()
//                   //为当前包路径
//                   .apis(RequestHandlerSelectors.basePackage("com.jike.system1.controller"))
//                   .paths(PathSelectors.any())
//                   .build();
//    }
//
//    /**
//     * 构建 api文档的详细信息函数,注意这里的注解引用的是哪个
//     *
//     * @return
//     */
//    private ApiInfo apiInfo() {
//        return new ApiInfoBuilder()
//                   .title("中央认证服务平台")
//                   .description("中央认证服务平台API接口文档")
//                   .termsOfServiceUrl("no terms of serviceUrl")
//                   .version("2.0")
//                   .build();
//    }
//
//}