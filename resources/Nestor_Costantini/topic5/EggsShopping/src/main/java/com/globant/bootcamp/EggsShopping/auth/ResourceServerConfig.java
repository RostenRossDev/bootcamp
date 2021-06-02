package com.globant.bootcamp.EggsShopping.auth;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;

@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {

	@Override
	public void configure(HttpSecurity http) throws Exception {
		// TODO Auto-generated method stub
		http.authorizeRequests()
		.antMatchers(HttpMethod.GET,"/api/v1/eggsShoping/home","/swagger-ui.html","/webjars/**","/swagger-resources/**","/v2/api-docs","/","/h2/**","/").permitAll()
		.antMatchers(HttpMethod.POST,"/api/v1/user/","/h2/**","/").permitAll()
		.anyRequest().authenticated();
	}

	
}
