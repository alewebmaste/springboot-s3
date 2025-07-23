package com.borba.storage;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class StorageApplication {

	public static void main(String[] args) {

		System.out.println("Aplicação iniciando...");
		SpringApplication.run(StorageApplication.class, args);
	}

}
