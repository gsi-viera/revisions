package com.avangenio.ecasa_inv.config;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import org.springframework.web.filter.GenericFilterBean;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


public class JwtFilter extends GenericFilterBean{
	
	@Override
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
		final HttpServletRequest request = (HttpServletRequest) servletRequest;
		final HttpServletResponse response = (HttpServletResponse) servletResponse;
		final String authHeader = request.getHeader("authorization");
		
		if(authHeader == null || !authHeader.startsWith("Bearer ")){
			throw new ServletException("Unauthorized");
		}
		
		final String token = authHeader.substring(7);
		Properties props = new Properties();
		props.load(new FileInputStream("target/classes/application.properties"));
		String secret = props.getProperty("app.secret.key");
		
		Claims claims = Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
		request.setAttribute("claims", claims);
		request.setAttribute("ecasa", servletRequest.getParameter("id"));
		filterChain.doFilter(request, response);
	}

}
