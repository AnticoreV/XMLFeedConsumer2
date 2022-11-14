package com.XMLFeedConsumer2;

import com.XMLFeedConsumer2.xml.StAXParser;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

@SpringBootApplication(exclude={DataSourceAutoConfiguration.class})
public class XmlFeedConsumer2Application {

	public static void main(String[] args) {
		SpringApplication.run(XmlFeedConsumer2Application.class, args);

	}

}
