package com.shj.mongoreact;

import com.shj.mongoreact.Model.Employee;
import com.shj.mongoreact.Repository.EmployeeRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.UUID;
import java.util.stream.Stream;

@SpringBootApplication
public class ReactiveMongoAndSpringWebFluxExampleApplication {

  @Bean
  CommandLineRunner employees(EmployeeRepository employeeRepository) {
    return args -> {
      employeeRepository
        .deleteAll()
        .subscribe(null, null, () -> Stream.of(
          new Employee(UUID.randomUUID().toString(), "SEJAME", "Henri", 2000L),
          new Employee(UUID.randomUUID().toString(), "SEJAME", "Gaelle", 1500L),
          new Employee(UUID.randomUUID().toString(), "SEJAME", "Audrey", 1800L)
        ).forEach(employee -> {
          employeeRepository
            .save(employee)
            .subscribe(System.out::println);
        }));
    };
  }

  public static void main(String[] args) {
   SpringApplication.run(ReactiveMongoAndSpringWebFluxExampleApplication.class, args);
  }

}
