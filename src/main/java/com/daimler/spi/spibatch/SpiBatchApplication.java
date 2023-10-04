package com.daimler.spi.spibatch;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan(basePackages = "com.daimler.spi.spibatch.data")
public class SpiBatchApplication {


	public static void main(String[] args) {
		SpringApplication.run(SpiBatchApplication.class, args);
	}

	

}
