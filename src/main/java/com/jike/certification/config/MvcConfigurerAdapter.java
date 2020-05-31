package com.jike.certification.config;

import com.jike.certification.interceptor.CommentInterceptor;
import com.jike.certification.interceptor.JurisdictionValidateInterceptor;
import com.jike.certification.interceptor.TokenValidateInterceptor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.ArrayList;
import java.util.List;


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

    @Bean
    public JurisdictionValidateInterceptor jurisdictionValidateInterceptor(){
        return new JurisdictionValidateInterceptor();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        log.debug("开始拦截");

        // 公共拦截
        registry.addInterceptor(commentInterceptor())
            .addPathPatterns("/api/**");

        // 登录验证
        registry.addInterceptor(tokenValidateInterceptor())
            .addPathPatterns("/api/pc/**")
            .excludePathPatterns("/api/pc/user/**")
            .excludePathPatterns("/api/pc/test/**")
            .excludePathPatterns("/api/pc/verifyCode/**");

        // 权限验证
        registry.addInterceptor(jurisdictionValidateInterceptor())
            .addPathPatterns("/**");
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        log.debug("addCorsMappings:添加请求头");
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

    private CorsConfiguration addcorsConfig() {
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        List<String> list = new ArrayList<>();
        list.add("*");
        corsConfiguration.setAllowedOrigins(list);
    /*
    // 请求常用的三种配置，*代表允许所有，当时你也可以自定义属性（比如header只能带什么，只能是post方式等等）
    */
        corsConfiguration.addAllowedOrigin("*");
        corsConfiguration.addAllowedHeader("*");
        corsConfiguration.addAllowedMethod("*");
        return corsConfiguration;
    }
    @Bean
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", addcorsConfig());
        return new CorsFilter(source);
    }
}

