package com.possystem.pos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.modelmapper.ModelMapper;
@SpringBootApplication
public class PointOfSalesSystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(PointOfSalesSystemApplication.class, args);
                //mvn spring-boot:run
	}
@Bean
public ModelMapper modelMapper(){
return new ModelMapper();
}
}
