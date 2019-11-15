package com.foodscribe;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan
public class FoodscribeApplication {

	public static void main(String[] args) {
		SpringApplication.run(FoodscribeApplication.class, args);
	}
}
