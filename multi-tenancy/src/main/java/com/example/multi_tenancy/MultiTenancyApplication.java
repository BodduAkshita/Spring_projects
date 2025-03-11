package com.example.multi_tenancy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;

import com.example.multi_tenancy.config.TenantInterceptor;

@SpringBootApplication
public class MultiTenancyApplication {

	public static void main(String[] args) {
		SpringApplication.run(MultiTenancyApplication.class, args);
		System.out.println("Hii");
	}
}
