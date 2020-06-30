package com.digitalharbor.eval.rest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.*;

import com.digitalharbor.eval.rest.security.AppProperties;

@SpringBootApplication
public class HospitalDhBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(HospitalDhBackendApplication.class, args);
	}
	
	@Bean
	public BCryptPasswordEncoder bCrypPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public SpringApplicationContext springApplicationContext() {
		return new SpringApplicationContext() ;
	}
	
	@Bean (name = "AppProperties")
	public AppProperties getAppProperties() {
		return new AppProperties() ;
	}
}
