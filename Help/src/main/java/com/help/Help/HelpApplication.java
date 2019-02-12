package com.help.Help;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;

import com.help.rest.controller.HelpController;
import com.help.util.FileStorageProperties;

@SpringBootApplication
@EnableAutoConfiguration

@EnableConfigurationProperties({
    FileStorageProperties.class
})
@ComponentScan(basePackageClasses = HelpController.class)

@ComponentScan(basePackages = "com.help")
public class HelpApplication {

	public static void main(String[] args) {
		SpringApplication.run(HelpApplication.class, args);
	}
}

