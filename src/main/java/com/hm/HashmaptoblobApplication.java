package com.hm;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class HashmaptoblobApplication {

	@Autowired
	private HelperClass helper;

	public static void main(String[] args) {
		SpringApplication.run(HashmaptoblobApplication.class, args);
	}
}
