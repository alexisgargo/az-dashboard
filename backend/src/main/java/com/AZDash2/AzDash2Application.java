package com.AZDash2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class AzDash2Application {

	public static void main(String[] args) {
		SpringApplication.run(AzDash2Application.class, args);
	}

	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(@SuppressWarnings("null") CorsRegistry registry) {
				registry.addMapping("/az_dashboard/engineer")
						.allowedOrigins("http://localhost:3000")
						.allowedMethods("GET", "POST") // Permitir métodos GET y POST
						.allowCredentials(true); // Permitir credenciales (por ejemplo, cookies)
				registry.addMapping("/az_dashboard/issue")
						.allowedOrigins("http://localhost:3000")
						.allowedMethods("POST") // Permitir métodos GET y POST
						.allowCredentials(true); // Permitir credenciales (por ejemplo, cookies)
				registry.addMapping("/az_dashboard/issues")
						.allowedOrigins("http://localhost:3000")
						.allowedMethods("GET"); // Permitir solo el método GET para esta ruta

			}
		};
	}

}
