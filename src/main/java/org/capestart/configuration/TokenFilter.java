package org.capestart.configuration;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.capestart.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;

@Component
public class TokenFilter implements Filter {

	@Autowired
	LoginService loginService;

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		try {
			if ("OPTIONS".equals(((HttpServletRequest)request).getMethod())) {
				((HttpServletResponse)response).setHeader("Access-Control-Allow-Origin", "*");
				((HttpServletResponse)response).setHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS");
		        ((HttpServletResponse)response).setHeader("Access-Control-Allow-Headers", "authorization, content-type");
				((HttpServletResponse)response).setStatus(HttpServletResponse.SC_OK);
			}else {
				String token =((HttpServletRequest)request).getHeader("Authorization");
				Claims claims = loginService.decodeToken(token);
				((HttpServletRequest)request).setAttribute("ADMIN", claims.get("ADMIN"));
				((HttpServletRequest)request).setAttribute("ID", claims.get("ID"));
				chain.doFilter(request, response);
			}
		} catch (Exception e) {
			((HttpServletResponse)response).sendError(HttpServletResponse.SC_UNAUTHORIZED);
		}
	}



}
