package com.dubg.verification.interceptor;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;

public class AuthenticationHandlerInterceptor implements HandlerInterceptor {
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		System.out.println("Auth");
		HttpSession httpSession = request.getSession();
		String userId = (String)httpSession.getAttribute("userId");
		System.out.println(userId);
		if (userId == null) {
			// TODO 認証エラー時のレスポンス MVCならログインやエラー画面に遷移だが、WebAPIなのでJSON形式？
			// response.sendRedirect("");
			PrintWriter out = response.getWriter();
			response.setContentType("application/json");
			response.setCharacterEncoding("UTF-8");
			out.print("{\"message\": \"error\"}");
			out.flush();
			return false;
		}
		// 認証済みならユーザーIDをリクエストに追加
		request.setAttribute("userId", userId);
		return true;
	}
}
