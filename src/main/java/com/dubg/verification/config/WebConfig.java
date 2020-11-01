package com.dubg.verification.config;

import java.util.Arrays;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.dubg.verification.interceptor.AuthenticationHandlerInterceptor;

@Configuration
public class WebConfig implements WebMvcConfigurer {
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry
			.addInterceptor(new AuthenticationHandlerInterceptor())
			.addPathPatterns("/**")
			.excludePathPatterns(Arrays.asList("/auth/login"));
	}
}
