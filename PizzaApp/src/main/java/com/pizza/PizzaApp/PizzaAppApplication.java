package com.pizza.PizzaApp;

import lombok.extern.java.Log;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@Log
public class PizzaAppApplication implements CommandLineRunner {
	private PizzaConfig pizza;

	public PizzaAppApplication(PizzaConfig pizza) {
		this.pizza = pizza;
	}
	public static void main(String[] args) {
		SpringApplication.run(PizzaAppApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		log.info(String.format("I want %s crust pizza, with %s and %s sauce",
				pizza.getCrust(),
				pizza.getTopping(),
				pizza.getSauce()
				));
	}
}
