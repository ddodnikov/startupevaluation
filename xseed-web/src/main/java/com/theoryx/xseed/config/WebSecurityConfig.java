package com.theoryx.xseed.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.session.HttpSessionEventPublisher;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.theoryx.xseed.model.User;
import com.theoryx.xseed.repository.UserRepository;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private UserRepository userModelRepository;

	@Autowired
	public void configAuthentication(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService()).passwordEncoder(new BCryptPasswordEncoder());
	}
	
	@Bean
	public HttpSessionEventPublisher httpSessionEventPublisher() {
	    return new HttpSessionEventPublisher();
	}

	@Bean
	protected UserDetailsService userDetailsService() {
		return new UserDetailsService() {
			@Override
			public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
				User user = userModelRepository.findByEmail(email);
				if (user != null) {
					return new org.springframework.security.core.userdetails.User(user.getEmail(),
							user.getHashedPassword(), user.getIsActive(), true, true, true,
							getGrantedAuthorities(user));
				} else {
					throw new UsernameNotFoundException("could not find user with email '" + email + "'");
				}
			}
		};
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable();
		http.sessionManagement().maximumSessions(1).expiredUrl("/login").and().invalidSessionUrl("/login");
		http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.NEVER);
		http.sessionManagement().sessionAuthenticationErrorUrl("/login");
		http.authorizeRequests()
		.antMatchers("/", "/index", "/updatenewuser" ,"/register","/login", "/reset-password", "/forgottenpassword", "/invitation", "/admin", "/createadmin","/403", "/home").permitAll()
		.antMatchers("/showallusers", "/showallstartups", "/addadmin", "/admins", "/algoquestions", 
				"/calculate", "/calculations", "/calculation-details", "/calculation-summary").access("hasRole('ROLE_ADMIN')")			 
		.antMatchers("/reports", "/progressreport", "/surveys", "/startupusers", "/startsurvey", "/snapshots", "/snapshotdetails", "/report-details").access("hasRole('ROLE_USER')")
		.anyRequest().authenticated()
		.and()
		.formLogin()
			.loginPage("/login")
			.defaultSuccessUrl("/home", true)
			.failureUrl("/login?status=error")
			.usernameParameter("username")
			.passwordParameter("password")
		.permitAll()
		.and()
		  .logout()
		  	.deleteCookies("JSESSIONID")
		  	.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
		  	.logoutSuccessUrl("/login?status=logout")
		  	.invalidateHttpSession(true)
		 .permitAll()
		 .and()
		 .exceptionHandling().accessDeniedPage("/403");
	  }
	
	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/css/*").antMatchers("/img/*").antMatchers("/js/*");
	}

	private List<GrantedAuthority> getGrantedAuthorities(User user) {
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		authorities.add(new SimpleGrantedAuthority("ROLE_" + user.getRole()));
		return authorities;
	}

}
