package org.chalenge3.Challenge_3BinarFood;

import org.chalenge3.Challenge_3BinarFood.controller.HomeController;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Challenge3BinarFoodApplication {

	public static void main(String[] args) {
		HomeController homeController = SpringApplication.run(Challenge3BinarFoodApplication.class, args)
				.getBean(HomeController.class);
		homeController.home();
	}

}
