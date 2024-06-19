package com.example.estatebookweb;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


@SpringBootApplication

public class EstateBookWebApplication extends SpringBootServletInitializer {
	static final Logger log =
			LoggerFactory.getLogger(EstateBookWebApplication.class);

	public static void main(String[] args) {

		SpringApplication.run(EstateBookWebApplication.class, args);
	}
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		return builder.sources(EstateBookWebApplication.class);
	}
}
