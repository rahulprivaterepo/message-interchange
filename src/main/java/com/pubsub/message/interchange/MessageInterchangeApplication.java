package com.pubsub.message.interchange;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "Publisher-Subscriber API", version = "1.0", description = "Message Exchange between Publisher & Subscriber"))
public class MessageInterchangeApplication {

	public static void main(String[] args) {
		SpringApplication.run(MessageInterchangeApplication.class, args);
	}

}
