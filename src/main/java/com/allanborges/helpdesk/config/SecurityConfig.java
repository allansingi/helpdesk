package com.allanborges.helpdesk.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;

import com.allanborges.helpdesk.security.JWTAuthenticationFilter;
import com.allanborges.helpdesk.security.JWTUtil;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

	private static final String[] PUBLIC_MATCHERS = { "/**", "/h2/**", "/login/**" };

	@Autowired
	private Environment env;

	@Autowired
	private JWTUtil jwtUtil;

	@Autowired
	private UserDetailsService detailsService;

	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfiguration) throws Exception {
		return authConfiguration.getAuthenticationManager();
	}

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http, AuthenticationConfiguration authConfiguration)
			throws Exception {

		if (Arrays.asList(env.getActiveProfiles()).contains("test")) {
			http.headers().frameOptions().disable();
		}
		http.cors();
		http.addFilter(new JWTAuthenticationFilter(authConfiguration.getAuthenticationManager(), jwtUtil));
		http.addFilter(
				new JWTAuthorizationFilter(authConfiguration.getAuthenticationManager(), jwtUtil, detailsService));

		return http.csrf().disable().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
				.authorizeHttpRequests().requestMatchers(PUBLIC_MATCHERS).permitAll().anyRequest().authenticated().and()
				.build();
	}

	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public CorsConfigurationSource corsConfigurationSource() {
		CorsConfiguration configuration = new CorsConfiguration().applyPermitDefaultValues();
		configuration.setAllowedMethods(Arrays.asList("POST", "GET", "PUT", "DELETE", "OPTIONS"));
		final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", configuration);
		return source;
	}
}