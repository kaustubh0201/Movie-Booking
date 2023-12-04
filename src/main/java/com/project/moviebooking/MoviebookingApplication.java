package com.project.moviebooking;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

/**
 * The main entry point for the Movie Booking application.
 * Configures and launches the Spring Boot application.
 */
@SpringBootApplication
@EnableCaching
public class MoviebookingApplication {

	/**
	 * The main method that starts the Spring Boot application.
	 *
	 * @param args Command-line arguments passed to the application.
	 */
	public static void main(String[] args) {
		SpringApplication.run(MoviebookingApplication.class, args);
	}
}