package com.yedam.app.security.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import jakarta.servlet.DispatcherType;

@Configuration
@EnableWebSecurity
public class SpringSecurityConfig {
	@Bean //비밀번호 암호화 : 단방향
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
		
		
	}
	@Bean // 메모리상 인증정보 등록
	InMemoryUserDetailsManager inMemoryUserDetailsService() {
	    UserDetails user = User.builder()
	            				.username("user1")
	            				.password(passwordEncoder().encode("1234"))
	            				.roles("USER") //ROLE_USER  앞에 ROLE_ 자동으로 붙음
	            				.build();
	    
	    UserDetails admin = User.builder()
								.username("admin1")
								.password("1234")
								.authorities("ROLE_ADMIN") 
								.build();
	    
	    return new InMemoryUserDetailsManager(user, admin);
	}
	
	@Bean
	SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http // Security가 적용될 URI
			.authorizeHttpRequests((authrize) -> authrize
					.dispatcherTypeMatchers(DispatcherType.FORWARD).permitAll()
					.requestMatchers("/", "/all").permitAll()
					.requestMatchers("/user/**").hasAnyRole("USER", "ADMIN")
					.requestMatchers("/admin/**").hasAuthority("ROLE_ADMIN")
					.anyRequest().authenticated()
					)
				
			.formLogin(formLogin->formLogin.usernameParameter("id").defaultSuccessUrl("/all"))
			.logout(logout -> logout
					.logoutSuccessUrl("/all")
					.invalidateHttpSession(true)
					.deleteCookies("JSESSIONID"));
		
		return http.build();
		
	}
	
}
