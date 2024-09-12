package com.app.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfiguration {
	
	@Bean
	public WebMvcConfigurer webMvcConfigurer()
	{
		return new WebMvcConfigurer() {
			
			public void addCoreMapping(CorsRegistry registry) {
				registry.addMapping("/**")
				.allowedMethods("GET","POST","PUT","DELETE")
				.allowedOrigins("*");
			}
		};
	}

}
