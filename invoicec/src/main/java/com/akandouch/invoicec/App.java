package com.akandouch.invoicec;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@Configuration
@EnableAutoConfiguration
@EnableMongoRepositories
public class App
{
    public static void main( String[] args )
    {
    	SpringApplication.run(App.class, args);
    }
}
