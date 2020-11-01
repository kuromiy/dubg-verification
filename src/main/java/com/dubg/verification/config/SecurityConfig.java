package com.dubg.verification.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.logout.HttpStatusReturningLogoutSuccessHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.dubg.verification.handler.auth.AuthenticationHandler;

@EnableWebSecurity
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter implements WebMvcConfigurer {
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
			.anyRequest().authenticated();

		http.exceptionHandling()
			.authenticationEntryPoint(authenticationHandler())
			.accessDeniedHandler(authenticationHandler());

		http.formLogin()
			.loginProcessingUrl("/auth/login").permitAll()
				.usernameParameter("userMail")
				.passwordParameter("userPass")
			.successHandler(authenticationHandler())
			.failureHandler(authenticationHandler());

		http.logout()
			.logoutUrl("/auth/logout")
			.invalidateHttpSession(true)
			.deleteCookies("JSESSIONID")
			.logoutSuccessHandler(logoutSuccessHandler());

		http.csrf()
			.disable();

		http.cors()
			.configurationSource(corsFilter());

		http.sessionManagement()
			.sessionFixation().newSession();
	}

	@Override
	public void addCorsMappings(CorsRegistry registry) {
		System.out.println("corssss");
		registry.addMapping("/**")
				.allowedOrigins("http://localhost:3000")
				.allowedMethods("GET", "POST", "PUT", "DELETE");
	}

	@Autowired
	public void confiureGlobal(
			AuthenticationManagerBuilder auth,
			@Qualifier("authUserDetailsService") UserDetailsService userDetailsService,
			PasswordEncoder passwordEncoder) throws Exception {
		System.out.println(userDetailsService);
		System.out.println(passwordEncoder);
		auth.eraseCredentials(true)
			.userDetailsService(userDetailsService);
			// .passwordEncoder(passwordEncoder);
	}

	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	CorsConfigurationSource corsFilter() {
		System.out.println("cors config");
		CorsConfiguration config = new CorsConfiguration();
		config.setAllowCredentials(true);
		config.addAllowedOrigin("http://localhost:3000");
		config.addAllowedMethod(CorsConfiguration.ALL);
		config.addAllowedHeader(CorsConfiguration.ALL);

		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", config.applyPermitDefaultValues());

		return source;
	}

	AuthenticationHandler authenticationHandler() {
		return new AuthenticationHandler();
	}

	LogoutSuccessHandler logoutSuccessHandler() {
		return new HttpStatusReturningLogoutSuccessHandler();
	}
}
