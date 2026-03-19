package com.spring.security.basic.auth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class SpringSecurityBasicAuthApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringSecurityBasicAuthApplication.class, args);
	}

}
