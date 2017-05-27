package com.mytijian.wormhole.web.interceptor;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * Created by wangchangpeng on 2017/5/19.
 */
@Configuration
@EnableWebMvc
public class WebAppConfig  extends WebMvcConfigurerAdapter {

    @Bean
    public UserAuthInterceptor getUserAuthInterceptor(){
        return new UserAuthInterceptor();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(getUserAuthInterceptor());
        super.addInterceptors(registry);
    }

}
