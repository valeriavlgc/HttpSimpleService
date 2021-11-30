package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@ServletComponentScan
@SpringBootApplication
public class EmpleadosHttpSimpleServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(EmpleadosHttpSimpleServiceApplication.class, args);
	}

}
