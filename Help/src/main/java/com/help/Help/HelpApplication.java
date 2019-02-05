package com.help.Help;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties({
    FileStorageProperties.class
})
public class HelpApplication {

	public static void main(String[] args) {
		SpringApplication.run(HelpApplication.class, args);
	}

}

