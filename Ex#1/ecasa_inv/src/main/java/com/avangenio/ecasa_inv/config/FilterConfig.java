package com.avangenio.ecasa_inv.config;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FilterConfig {
	@Bean
	public FilterRegistrationBean jwtFilter() {
		FilterRegistrationBean filter= new FilterRegistrationBean();
		filter.setFilter(new JwtFilter());
		filter.addUrlPatterns("/ecasa/sections/*");
		filter.addUrlPatterns("/ecasa/products/*");
		filter.addUrlPatterns("/ecasa/inventories/*");
		return filter;
	}

}
