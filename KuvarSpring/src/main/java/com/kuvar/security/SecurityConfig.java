package com.kuvar.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	@Autowired
 	UserDetailProvider userDetailsService;
	
	@Bean
	public PasswordEncoder getPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
	   auth.userDetailsService( userDetailsService);
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
	   http.authorizeRequests().
	   antMatchers("/","/login","/signup", "/searchController/**").permitAll(). // svima daje pristupa
	   antMatchers("/admin/**", "/categoryController/**").hasRole("ADMIN"). // samo admin moze da pristupi, ostale odbija
	   antMatchers("/users/**", "/controller/**", "/recipeController/**").hasAnyRole("ADMIN","USER"). // samo autentifikovani korisnici mogu da pristupe (admin i user)
	   and().formLogin().
	   loginPage("/login.jsp").
	   loginProcessingUrl("/login").
	   defaultSuccessUrl("/index.jsp").failureForwardUrl("/error.jsp").and().
	   logout().invalidateHttpSession(true)
	   .logoutSuccessUrl("/login.jsp").and().
	   exceptionHandling().accessDeniedPage("/access_denied.jsp").
	   and().rememberMe().
	   and().csrf().disable();
	}

}
