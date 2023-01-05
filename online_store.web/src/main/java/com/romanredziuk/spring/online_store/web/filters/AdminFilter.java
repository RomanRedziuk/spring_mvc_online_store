package com.romanredziuk.spring.online_store.web.filters;

import static com.romanredziuk.spring.online_store.web.controllers.SignInController.*;
import static com.romanredziuk.spring.online_store.persistence.dto.RoleDto.*;

import java.io.IOException;

import com.romanredziuk.spring.online_store.persistence.entities.User;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebFilter("/admin/*")
public class AdminFilter implements Filter {
       
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		User user = (User)((HttpServletRequest)request).getSession().getAttribute(LOGGED_IN_USER_ATTR);
		if (user != null) {
			if (user.getRoleName().equals(ADMIN_ROLE_NAME)) {
				chain.doFilter(request, response);
			} else {
				((HttpServletResponse)response).sendError(403);
			}
		} else {
			((HttpServletResponse)response).sendRedirect(
					request.getScheme()
				      + "://"
				      + request.getServerName()
				      + ":"
				      + request.getServerPort()
				      + request.getServletContext().getContextPath() + "/signin");
		}
		
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		
	}

	@Override
	public void destroy() {
		
	}

}
