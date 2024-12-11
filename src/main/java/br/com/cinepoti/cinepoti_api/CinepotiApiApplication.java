package br.com.cinepoti.cinepoti_api;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CinepotiApiApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(CinepotiApiApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
        System.out.println("Cinepoti API is running...");
	}
}
