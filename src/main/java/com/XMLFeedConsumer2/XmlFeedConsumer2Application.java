package com.XMLFeedConsumer2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

//@SpringBootApplication(exclude =  {DataSourceAutoConfiguration.class })
@SpringBootApplication
public class XmlFeedConsumer2Application {

	public static void main(String[] args) {
		SpringApplication.run(XmlFeedConsumer2Application.class, args);
	}

}
