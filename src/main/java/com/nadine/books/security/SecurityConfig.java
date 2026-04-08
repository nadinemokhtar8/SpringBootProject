package com.nadine.books.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

	private final UserDetailsService userDetailsService;
	private final PasswordEncoder passwordEncoder;

	public SecurityConfig(UserDetailsService userDetailsService, PasswordEncoder passwordEncoder) {
		this.userDetailsService = userDetailsService;
		this.passwordEncoder = passwordEncoder;
	}

	@Bean
	DaoAuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider(userDetailsService);
		authProvider.setPasswordEncoder(passwordEncoder);
		return authProvider;
	}

	@Bean
	AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration)
			throws Exception {
		return authenticationConfiguration.getAuthenticationManager();
	}

	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http.authenticationProvider(authenticationProvider()).csrf(csrf -> csrf.ignoringRequestMatchers("/api/**"))
				.authorizeHttpRequests(auth -> auth.requestMatchers("/", "/login", "/accessDenied", "/webjars/**")
						.permitAll()
						.requestMatchers("/showCreate", "/saveProduit", "/modifierLivre", "/BookList/modifierLivre")
						.hasAnyAuthority("ADMIN", "AGENT","USER")
						.requestMatchers("/supprimerProduit", "/BookList/supprimerProduit").hasAuthority("ADMIN")
						.requestMatchers(HttpMethod.GET, "/BookList").permitAll().requestMatchers("/BookList/**")
						.authenticated().requestMatchers(HttpMethod.GET, "/api/**").hasAnyAuthority("ADMIN", "AGENT")
						.requestMatchers(HttpMethod.POST, "/api/**").hasAnyAuthority("ADMIN", "AGENT")
						.requestMatchers(HttpMethod.PUT, "/api/**").hasAnyAuthority("ADMIN", "AGENT")
						.requestMatchers(HttpMethod.DELETE, "/api/**").hasAuthority("ADMIN").anyRequest()
						.authenticated())
				.exceptionHandling(exception -> exception.accessDeniedPage("/accessDenied"))
				.httpBasic(Customizer.withDefaults())
				.formLogin(form -> form.loginPage("/login").defaultSuccessUrl("/BookList", true).permitAll())
				.logout(logout -> logout.logoutSuccessUrl("/login"));

		return http.build();
	}
}
