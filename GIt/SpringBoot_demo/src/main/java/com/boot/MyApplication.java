package com.boot;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;



@SpringBootApplication
public class MyApplication {  
	private static final Logger logger = LoggerFactory.getLogger(MyApplication.class);
	public static void main(String[] args) {
		SpringApplication.run(MyApplication.class, args);
		logger.debug("--Application Started--");
		
		logger.trace("Trace Message!");
		logger.debug("Debug Message!");
		logger.info("Info Message!");
		logger.warn("Warn Message!");
		logger.error("Error Message!");
    }       
}            