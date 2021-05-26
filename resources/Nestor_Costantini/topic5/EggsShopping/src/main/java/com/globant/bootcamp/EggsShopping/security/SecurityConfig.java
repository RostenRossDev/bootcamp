package com.globant.bootcamp.EggsShopping.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

//@Configuration
//@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

		
	@Bean
	@Override
	protected AuthenticationManager authenticationManager() throws Exception {
		// TODO Auto-generated method stub
		return super.authenticationManager();
	}

	//@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
    @Override
	public void configure(HttpSecurity http) throws Exception {
		// TODO Auto-generated method stub
		http.authorizeRequests()
		.anyRequest().authenticated()
		.and()
		.csrf().disable()
		.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
	}
    
    @Autowired
    public void configuererGlobal(AuthenticationManagerBuilder builder)throws Exception {
    	
    	PasswordEncoder encoder = passwordEncoder();
    	UserBuilder users = User.builder().passwordEncoder(password -> {
    		return encoder.encode(password);
    	});
    	
    	builder.inMemoryAuthentication()
    	.withUser(users.username("Florencia").password("12345").roles("ADMIN","USER"))
    	.withUser(users.username("Andres").password("12345").roles("USER"))
    	.withUser(users.username("Pepe").password("12345").roles("USER"))
    	.withUser(users.username("Jose").password("12345").roles("USER"))
    	.withUser(users.username("Cristina").password("12345").roles("USER"));
    }
    
    
}