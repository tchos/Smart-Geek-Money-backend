package com.geekinstitut.smartmoney;

import jakarta.persistence.Entity;
import lombok.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
public class SmartmoneyApplication {

	public static void main(String[] args) {
		SpringApplication.run(SmartmoneyApplication.class, args);
	}

}
