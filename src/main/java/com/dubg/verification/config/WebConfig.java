package com.dubg.verification.config;

import java.util.Arrays;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
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

	@Override
	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/**")
				.allowedOrigins("http://localhost:3000")
				.allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS", "HEAD", "PATCH")
				.allowedHeaders("X-Requested-With", "Origin", "X-Csrftoken", "Content-Type", "Accept")
				// .allowedHeaders("X-Requested-With, Origin, X-Csrftoken, Content-Type, Accept")
				.allowCredentials(true);
	}
}
