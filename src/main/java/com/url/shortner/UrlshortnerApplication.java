package com.url.shortner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.url.shortner.model.Url;
import com.url.shortner.model.UrlRepo;

@SpringBootApplication
public class UrlshortnerApplication {
	@Autowired
	 private UrlRepo urlRepo;

	public static void main(String[] args) {
		SpringApplication.run(UrlshortnerApplication.class, args);
		
	}
	 @Bean
	    public CommandLineRunner demoData() {
	        return args -> {
	            // This runs after Spring context is fully initialized
	            urlRepo.insert(new Url(1L, "https://example.com"));
	            System.out.println("URL inserted successfully!");
	            
	          
	        };
	    }

}
