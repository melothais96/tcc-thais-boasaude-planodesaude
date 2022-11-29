package com.boasaude.planodesaude.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
public class BasicSecurityConfig {
	
	@Autowired
	private UserDetailsService userDetailsService;	
	
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService);
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	protected SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http.authorizeRequests()
		.antMatchers("/planodesaude/logar").permitAll()
		.antMatchers("/planodesaude/cadastrar").permitAll()
		.antMatchers(HttpMethod.GET,"/planodesaude").permitAll()
		.antMatchers(HttpMethod.GET,"/planodesaude/categoria").permitAll()
        .antMatchers(HttpMethod.GET,"/planodesaude/nome/*").permitAll()
        .antMatchers(HttpMethod.DELETE, "planodesaude/id/*").permitAll()
        .antMatchers(HttpMethod.PUT, "planodesaude/id/{id}").permitAll().antMatchers(HttpMethod.GET,"/planodesaude").permitAll()
        .antMatchers(HttpMethod.GET,"/classificacao").permitAll()
        .antMatchers(HttpMethod.GET,"/classificacao/tipo/*").permitAll()
        .antMatchers(HttpMethod.GET,"/classificacao/id/*").permitAll()
        .antMatchers(HttpMethod.DELETE, "/classificacao/id/*").permitAll()
        .antMatchers(HttpMethod.PUT, "/classificacao").permitAll()
        .antMatchers(HttpMethod.POST, "/classificacao").permitAll()
        .antMatchers(HttpMethod.GET,"/categoria").permitAll()
        .antMatchers(HttpMethod.GET,"/categoria/tipo/*").permitAll()
        .antMatchers(HttpMethod.GET,"/categoria/id/*").permitAll()
        .antMatchers(HttpMethod.DELETE, "/categoria/id/*").permitAll()
        .antMatchers(HttpMethod.PUT, "/categoria").permitAll()
        .antMatchers(HttpMethod.POST, "/categoria").permitAll()
		.antMatchers("/h2-console/**").permitAll()		
		.anyRequest().authenticated()
		.and().httpBasic()
		.and().sessionManagement()
		.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
		.and().cors()
		.and().csrf().disable();
		
		 http.csrf().disable();
		 http.headers().frameOptions().disable();
		
		 return http.build();
		 
	}
	
	@Bean
	protected WebSecurityCustomizer webSecurityCustomizer(){
	    return (web) -> web.ignoring().antMatchers("/swagger-ui.hmtl", "/v3/api-docs/**");
	}
}
