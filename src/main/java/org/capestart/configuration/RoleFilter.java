package org.capestart.configuration;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;

public class RoleFilter implements Filter {

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		boolean isAdmin = (boolean) request.getAttribute("ADMIN");
		if(isAdmin)
			chain.doFilter(request, response);
		else
			((HttpServletResponse)response).sendError(HttpServletResponse.SC_UNAUTHORIZED);
	}

}
