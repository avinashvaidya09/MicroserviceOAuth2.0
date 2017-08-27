package com.mycode.oauth.security.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;

/**
 * Configures the resource for the application.
 *
 */
@Configuration
@EnableResourceServer
public class AppResourceServerConfig extends ResourceServerConfigurerAdapter{

	 private static final String RESOURCE_ID = "secure_rest";
     
	    @Override
	    public void configure(ResourceServerSecurityConfigurer resources) {
	        resources.resourceId(RESOURCE_ID).stateless(false);
	    }
	 
	    @Override
	    public void configure(HttpSecurity http) throws Exception {
	        http.
	        anonymous().and().authorizeRequests().antMatchers("/microservice/**").authenticated();
	    }
}
