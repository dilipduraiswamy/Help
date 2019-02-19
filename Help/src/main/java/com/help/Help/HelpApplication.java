package com.help.Help;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import com.help.dao.HelpRepo;
import com.help.rest.controller.HelpController;
import com.help.util.FileStorageProperties;

@SpringBootApplication
@EnableAutoConfiguration
@EnableConfigurationProperties
({
    FileStorageProperties.class
})
@ComponentScan(basePackageClasses = HelpController.class)

@ComponentScan(basePackages = "com.help.*")
@EnableMongoRepositories(basePackageClasses = HelpRepo.class)
public class HelpApplication {

	public static void main(String[] args) {
		SpringApplication.run(HelpApplication.class, args);
	}
}

