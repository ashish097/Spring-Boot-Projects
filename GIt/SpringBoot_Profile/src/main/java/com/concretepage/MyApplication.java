package com.concretepage;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class MyApplication {
	

	public static void main(String[] args) {
		SpringApplication.run(MyApplication.class, args);
		/*SpringApplication application = new SpringApplication(MyApplication.class);
		application.setAdditionalProfiles("dev","animal_dev");
		application.run(args);*/
    }       
}            