package com.shj.mongoreact;

import com.shj.mongoreact.Configuration.CustomReactiveMongoRepositoryFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.data.mongodb.core.MongoOperations;

@SpringBootApplication
public class ReactiveMongoAndSpringWebFluxExampleApplication {

  public static void main(String[] args) {
   SpringApplication.run(ReactiveMongoAndSpringWebFluxExampleApplication.class, args);
  }
}
