package org.java.auth.config;

import org.java.auth.serv.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class AuthConf {
	@Bean
	SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

		http.csrf().disable().cors().disable().authorizeHttpRequests()
//		.requestMatchers("/home").hasAnyAuthority("USER", "ADMIN")
//				.requestMatchers("/pizza/create").hasAuthority("ADMIN").requestMatchers("/pizza/edit/**")
//				.hasAuthority("ADMIN").requestMatchers("/pizza/delete/**").hasAuthority("ADMIN")
//				.requestMatchers("/ingredient/**").hasAuthority("ADMIN")
//				.requestMatchers("/offert/**").hasAuthority("ADMIN")
				.requestMatchers("/**").permitAll().and().formLogin().and().logout();

		return http.build();
	}

	@Bean
	UserDetailsService userDetailsService() {
		return new UserService();
	}

	@Bean
	public static PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	DaoAuthenticationProvider authenticationProvider() {

		DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();

		authProvider.setUserDetailsService(userDetailsService());
		authProvider.setPasswordEncoder(passwordEncoder());

		return authProvider;
	}

}