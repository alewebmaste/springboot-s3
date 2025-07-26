package com.borba.storage;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class StorageApplication {

	public static void main(String[] args) {

		System.out.println("Aplicação iniciando...");
		SpringApplication.run(StorageApplication.class, args);
	}

}
