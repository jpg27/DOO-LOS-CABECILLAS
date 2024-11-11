package co.com.pgvl.initializer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"co.com.pgvl.controller"})
public class PGVLApplication {

	public static void main(String[] args) {
		SpringApplication.run(PGVLApplication.class, args);
	}

}
