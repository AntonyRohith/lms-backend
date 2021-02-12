package org.capestart.configuration;


import javax.servlet.Filter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories("org.capestart.repository")
public class ApplicationConfiguration {
	
	@Autowired
	TokenFilter tokenFilter;
	
	@Bean
	@Order(1)
    public FilterRegistrationBean<Filter> TokenFilter() {
        FilterRegistrationBean<Filter> registration = new FilterRegistrationBean<Filter>();
        registration.setFilter(tokenFilter);
        registration.addUrlPatterns("/admin/*","/user/*");
        return registration;
    }
	
	@Bean
    public FilterRegistrationBean<Filter> roleFilter() {
        FilterRegistrationBean<Filter> registration = new FilterRegistrationBean<Filter>();
        registration.setFilter(new RoleFilter());
        registration.addUrlPatterns("/admin/*");
        return registration;
    }

}
