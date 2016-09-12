package com.ebook.polaris.filter;


import java.io.PrintWriter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;

public class CaptchaFormAuthenticationFilter extends FormAuthenticationFilter {

	  
	/**
	 * 所有不通过的方法请求都会经过的方法。
	 */
	@Override
	protected boolean onAccessDenied(ServletRequest request,
			ServletResponse response) throws Exception {

		if (isLoginRequest(request, response)) {
			if (isLoginSubmission(request, response)) {
				if ("XMLHttpRequest"
						.equalsIgnoreCase(((HttpServletRequest) request)
								.getHeader("X-Requested-With"))) {// 不是ajax请求
				}
				return executeLogin(request, response);
			} else {
				// allow them to see the login page ;)
				return true;
			}
		} else {
			if (!"XMLHttpRequest"
					.equalsIgnoreCase(((HttpServletRequest) request)
							.getHeader("X-Requested-With"))) {// 不是ajax请求
				saveRequestAndRedirectToLogin(request, response);
			} else {
				response.setCharacterEncoding("UTF-8");
				PrintWriter out = response.getWriter();
				out.println("{203}");
				out.flush();
				out.close();
			}
			return false;
		}
	}
}