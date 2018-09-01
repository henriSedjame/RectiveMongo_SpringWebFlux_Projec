package com.shj.mongoreact.Configuration;

import com.shj.mongoreact.Model.Employee;
import com.shj.mongoreact.Model.EmployeeEvent;
import com.shj.mongoreact.Repository.EmployeeRepository;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.util.function.Tuple2;

import java.time.Duration;
import java.util.Date;
import java.util.stream.Stream;

/**
 * @Project Reactive Mongo and Spring webFlux Project
 * @Author Henri Joel SEDJAME
 * @Date 01/09/2018
 * @Class purposes : .......
 */
@Component
public class RouterHandlers {
  private EmployeeRepository employeeRepository;

  public RouterHandlers(EmployeeRepository employeeRepository) {
    this.employeeRepository = employeeRepository;
  }

  public Mono<ServerResponse> getAll(ServerRequest serverRequest) {
    return ServerResponse.ok()
      .body(employeeRepository.findAll(), Employee.class);
  }

  public Mono<ServerResponse> getById(ServerRequest serverRequest) {
    String id = serverRequest.pathVariable("id");
    return ServerResponse.ok()
      .body(employeeRepository.findById(id), Employee.class);
  }

  public Mono<ServerResponse> getEvents(ServerRequest serverRequest) {
    String id = serverRequest.pathVariable("id");
    return ServerResponse.ok()
      .contentType(MediaType.TEXT_EVENT_STREAM)
      .body(
        employeeRepository.findById(id)
          .flatMapMany(employee -> {
            Flux<Long> interval = Flux.interval(Duration.ofSeconds(2));
            Flux<EmployeeEvent> employeeEventFlux = Flux.fromStream(Stream.generate(() -> new EmployeeEvent(employee, new Date())));
            return Flux.zip(interval, employeeEventFlux)
              .map(Tuple2::getT2);
          }), EmployeeEvent.class
      );
  }
}
