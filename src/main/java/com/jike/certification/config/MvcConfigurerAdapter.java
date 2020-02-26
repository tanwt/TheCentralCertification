package com.jike.certification.config;

import com.jike.certification.interceptor.CommentInterceptor;
import com.jike.certification.interceptor.TokenValidateInterceptor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


/**
 * mvc配置类
 *
 * @author wentong
 */
@Slf4j
@Configuration
public class MvcConfigurerAdapter implements WebMvcConfigurer {

    @Bean
    public CommentInterceptor commentInterceptor() {
        return new CommentInterceptor();
    }
    @Bean
    public TokenValidateInterceptor tokenValidateInterceptor() {
        return new TokenValidateInterceptor();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {

//        公共拦截
        registry.addInterceptor(commentInterceptor())
            .addPathPatterns("/api/**");

//        登录验证
        registry.addInterceptor(tokenValidateInterceptor())
            .addPathPatterns("/api/pc/**")
            .excludePathPatterns("/api/pc/user/**")
            .excludePathPatterns("/api/pc/test/**")
            .excludePathPatterns("/api/pc/verifyCode/**");
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        //添加映射路径
        registry.addMapping("/**")
            //放行哪些原始域
            .allowedOrigins("*")
            //是否发送Cookie信息
            .allowCredentials(true)
            //放行哪些原始域(请求方式)
            .allowedMethods("GET", "POST", "PUT", "DELETE")
            //放行哪些原始域(头部信息)
            .allowedHeaders("*");
    }
}

