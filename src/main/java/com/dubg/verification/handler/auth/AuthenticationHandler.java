package com.dubg.verification.handler.auth;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.boot.web.servlet.server.Session.Cookie;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.WebAttributes;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

public class AuthenticationHandler implements AuthenticationSuccessHandler, AuthenticationFailureHandler, AuthenticationEntryPoint, AccessDeniedHandler {

	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException exception) throws IOException, ServletException {
		System.out.println("認証成功");
		if (response.isCommitted()) {
			System.out.println("response has commited");
			return;
		}

		response.setStatus(HttpStatus.OK.value());
		// clearAuthenticationAttributes(request);
	}

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
		System.out.println("認証失敗");
		response.sendError(HttpStatus.FORBIDDEN.value(), HttpStatus.FORBIDDEN.getReasonPhrase());
	}

	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException authException) throws IOException, ServletException {
		System.out.println("認可失敗: commence");
//		List<javax.servlet.http.Cookie> coolkie = Arrays.asList(request.getCookies());
//		coolkie.forEach(value -> System.out.println(value.getName() + ":" + value.getValue()));
		if (response.isCommitted()) {
			System.out.println("response has commited");
			return;
		}
		response.sendError(HttpStatus.UNAUTHORIZED.value(), HttpStatus.UNAUTHORIZED.getReasonPhrase());
	}

	@Override
	public void handle(HttpServletRequest request, HttpServletResponse response,
			AccessDeniedException accessDeniedException) throws IOException, ServletException {
		System.out.println("認可失敗: handle");
		response.sendError(HttpStatus.FORBIDDEN.value(), HttpStatus.FORBIDDEN.getReasonPhrase());
	}

	private void clearAuthenticationAttributes(HttpServletRequest request) {
		HttpSession httpSession = request.getSession(false);

		if (httpSession == null) {
			return;
		}
		httpSession.removeAttribute(WebAttributes.AUTHENTICATION_EXCEPTION);
	}
}
